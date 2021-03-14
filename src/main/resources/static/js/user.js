function login() {
    var uname = document.getElementById("uname").value;
    if(uname!=null && uname.trim()!="" ){
        document.getElementById("loginForm").submit();
    }

}