'use strict';

Object.defineProperty(exports, '__esModule', { value: true });

function _interopDefault (ex) { return (ex && (typeof ex === 'object') && 'default' in ex) ? ex['default'] : ex; }

var crypto = _interopDefault(require('crypto'));

function createCommonjsModule(fn, module) {
	return module = { exports: {} }, fn(module, module.exports), module.exports;
}

var jwt_1 = createCommonjsModule(function (module) {
/*
 * jwt-simple
 *
 * JSON Web Token encode and decode module for node.js
 *
 * Copyright(c) 2011 Kazuhito Hokamura
 * MIT Licensed
 */

/**
 * module dependencies
 */



/**
 * support algorithm mapping
 */
var algorithmMap = {
  HS256: 'sha256',
  HS384: 'sha384',
  HS512: 'sha512',
  RS256: 'RSA-SHA256'
};

/**
 * Map algorithm to hmac or sign type, to determine which crypto function to use
 */
var typeMap = {
  HS256: 'hmac',
  HS384: 'hmac',
  HS512: 'hmac',
  RS256: 'sign'
};


/**
 * expose object
 */
var jwt = module.exports;


/**
 * version
 */
jwt.version = '0.5.6';

/**
 * Decode jwt
 *
 * @param {Object} token
 * @param {String} key
 * @param {Boolean} [noVerify]
 * @param {String} [algorithm]
 * @return {Object} payload
 * @api public
 */
jwt.decode = function jwt_decode(token, key, noVerify, algorithm) {
  // check token
  if (!token) {
    throw new Error('No token supplied');
  }
  // check segments
  var segments = token.split('.');
  if (segments.length !== 3) {
    throw new Error('Not enough or too many segments');
  }

  // All segment should be base64
  var headerSeg = segments[0];
  var payloadSeg = segments[1];
  var signatureSeg = segments[2];

  // base64 decode and parse JSON
  var header = JSON.parse(base64urlDecode(headerSeg));
  var payload = JSON.parse(base64urlDecode(payloadSeg));

  if (!noVerify) {
    if (!algorithm && /BEGIN( RSA)? PUBLIC KEY/.test(key.toString())) {
      algorithm = 'RS256';
    }

    var signingMethod = algorithmMap[algorithm || header.alg];
    var signingType = typeMap[algorithm || header.alg];
    if (!signingMethod || !signingType) {
      throw new Error('Algorithm not supported');
    }

    // verify signature. `sign` will return base64 string.
    var signingInput = [headerSeg, payloadSeg].join('.');
    if (!verify(signingInput, key, signingMethod, signingType, signatureSeg)) {
      throw new Error('Signature verification failed');
    }

    // Support for nbf and exp claims.
    // According to the RFC, they should be in seconds.
    if (payload.nbf && Date.now() < payload.nbf*1000) {
      throw new Error('Token not yet active');
    }

    if (payload.exp && Date.now() > payload.exp*1000) {
      throw new Error('Token expired');
    }
  }

  return payload;
};


/**
 * Encode jwt
 *
 * @param {Object} payload
 * @param {String} key
 * @param {String} algorithm
 * @param {Object} options
 * @return {String} token
 * @api public
 */
jwt.encode = function jwt_encode(payload, key, algorithm, options) {
  // Check key
  if (!key) {
    throw new Error('Require key');
  }

  // Check algorithm, default is HS256
  if (!algorithm) {
    algorithm = 'HS256';
  }

  var signingMethod = algorithmMap[algorithm];
  var signingType = typeMap[algorithm];
  if (!signingMethod || !signingType) {
    throw new Error('Algorithm not supported');
  }

  // header, typ is fixed value.
  var header = { typ: 'JWT', alg: algorithm };
  if (options && options.header) {
    assignProperties(header, options.header);
  }

  // create segments, all segments should be base64 string
  var segments = [];
  segments.push(base64urlEncode(JSON.stringify(header)));
  segments.push(base64urlEncode(JSON.stringify(payload)));
  segments.push(sign(segments.join('.'), key, signingMethod, signingType));

  return segments.join('.');
};

/**
 * private util functions
 */

function assignProperties(dest, source) {
  for (var attr in source) {
    if (source.hasOwnProperty(attr)) {
      dest[attr] = source[attr];
    }
  }
}

function verify(input, key, method, type, signature) {
  if(type === "hmac") {
    return (signature === sign(input, key, method, type));
  }
  else if(type == "sign") {
    return crypto.createVerify(method)
                 .update(input)
                 .verify(key, base64urlUnescape(signature), 'base64');
  }
  else {
    throw new Error('Algorithm type not recognized');
  }
}

function sign(input, key, method, type) {
  var base64str;
  if(type === "hmac") {
    base64str = crypto.createHmac(method, key).update(input).digest('base64');
  }
  else if(type == "sign") {
    base64str = crypto.createSign(method).update(input).sign(key, 'base64');
  }
  else {
    throw new Error('Algorithm type not recognized');
  }

  return base64urlEscape(base64str);
}

function base64urlDecode(str) {
  return Buffer.from(base64urlUnescape(str), 'base64').toString();
}

function base64urlUnescape(str) {
  str += new Array(5 - str.length % 4).join('=');
  return str.replace(/\-/g, '+').replace(/_/g, '/');
}

function base64urlEncode(str) {
  return base64urlEscape(Buffer.from(str).toString('base64'));
}

function base64urlEscape(str) {
  return str.replace(/\+/g, '-').replace(/\//g, '_').replace(/=/g, '');
}
});

var jwtSimple = jwt_1;

const passSecret = 'dx8ygdpzcm97qcfennu0q'; //用于用户数据库密码加密的密钥，使用一个比较长的随机字符串即可，正式上线是切记更换

const tokenExp = 30 * 24 * 3600000;

var constants = {
	passSecret,
	tokenExp
};

const {
	passSecret: passSecret$1
} = constants;

function encryptPassword(password) {
	const hmac = crypto.createHmac('sha1', passSecret$1.toString('ascii'));
	hmac.update(password);
	return hmac.digest('hex');
}

var encryptPassword_1 = {
	encryptPassword
};

const db = uniCloud.database();
async function validateToken(token) {
	const userFromToken = JSON.parse(Buffer.from(token.split('.')[1], 'base64').toString());
	const userInDB = await db.collection('user').where(userFromToken).get();
	if (userInDB.data.length !== 1) {
		return {
			code: -1,
			errCode: 'TOKEN_INVALID',
			msg: '查无此人'
		}
	}
	const userInfoDB = userInDB.data[0];
	let userInfoDecode;

	userInfoDecode = jwtSimple.decode(token, userInfoDB.tokenSecret);

	function checkUser(userFromToken, userInfoDB) {
		return Object.keys(userFromToken).every(function(item) {
			return userFromToken[item] === userInfoDB[item] && userFromToken[item] === userInfoDecode[item]
		})
	}


	if (userInfoDB.exp > Date.now() && checkUser(userFromToken, userInfoDB)) {
		return {
			code: 0,
			username: userInfoDB.username,
			msg: 'token验证成功'
		}
	}

	if (userInfoDB.exp < Date.now()) {
		return {
			code: -3,
			errCode: 'TOKEN_EXPIRED',
			msg: 'token已失效'
		}
	}

	return {
		code: -2,
		errCode: 'TOKEN_INVALID',
		msg: 'token无效'
	}

}

var validateToken_1 = {
	validateToken
};

const {
	tokenExp: tokenExp$1
} = constants;
const {
	encryptPassword: encryptPassword$1
} = encryptPassword_1;
const {
	validateToken: validateToken$1
} = validateToken_1;

const db$1 = uniCloud.database();

async function signUp(event) {
	const {
		username,
		password
	} = event;

	let userInfo = {
		username
	};

	const userInDB = await db$1.collection('user').where(userInfo).get();

	let tokenSecret = crypto.randomBytes(16).toString('hex'),
		token = jwtSimple.encode(userInfo, tokenSecret);

	let userUpdateResult;
	if (userInDB.data && userInDB.data.length === 0) {
		userUpdateResult = await db$1.collection('user').add({
			...userInfo,
			password: encryptPassword$1(password),
			tokenSecret,
			exp: Date.now() + tokenExp$1
		});
	} else {
		return {
			code: -1,
			msg: '此操作员已添加，请勿重复添加'
		}
	}

	if (userUpdateResult.id || userUpdateResult.affectedDocs === 1) {
		return {
			code: 0,
			token,
			msg: '注册成功'
		}
	}

	return {
		code: -1,
		msg: '注册失败'
	}
}

// 批量注册操作员账号

async function signUpMany(event) {
	if (event.username) {
		return await signUpByAdmin(event);
	}
	const userList = [{
		username: 'admin',
		password: '123456'
	}];

	let resultList = [];
	for (let i = 0; i < userList.length; i++) {
		const res = await signUp(userList[i]);
		resultList.push(res.code);
	}
	
	let signUpManyResult = resultList.every((item) => {
		return item === 0
	});
	
	if (signUpManyResult) {
		return {
			msg: '操作员账号注册完成',
			userList
		}
	} else {
		return {
			msg: '一个或多个操作员账号注册失败，请检查是否已经添加了操作员账号'
		}
	}
}

// 超级管理员添加需认证是否登录
async function signUpByAdmin(event) {
	let operator_username = event.operator_username;
	
	if (!operator_username) {
		let validateResult;
		try {
			validateResult = await validateToken$1(event.token);
		} catch (e) {
			//TODO handle the exception
			return {
				code: -3,
				errCode: 'TOKEN_INVALID',
				msg: '登录状态无效，请重新登录'
			}
		}
	
		if (validateResult.code !== 0) {
			return {
				code: -3,
				errCode: 'TOKEN_INVALID',
				msg: '登录状态无效，请重新登录'
			}
		}
		operator_username = validateResult.username;
	} else {
		const userCollection = db$1.collection('user');
		let checkOperatorResult = await userCollection.where({
			username: operator_username
		}).get();
		if (checkOperatorResult.data && checkOperatorResult.data.length === 0) {
			return {
				code: -4,
				errCode: 'OPERATOR_ID_INVALID',
				msg: '操作员信息无效，请重新扫码录入'
			}
		}
	}
	return await signUp(event);
}
var main = signUpMany;

var createUser = {
	main: main
};

exports.default = createUser;
exports.main = main;
