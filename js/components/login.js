function login () {

    var accountDiv = document.createElement("div");
    accountDiv.classList.add("account");

    var idSpan = document.createElement('span');
    idSpan.innerHTML = "ID ";
    accountDiv.appendChild(idSpan);
    var idInput = document.createElement("input");
    accountDiv.appendChild(idInput);

    var passwordSpan = document.createElement('span');
    passwordSpan.innerHTML = "Password ";
    accountDiv.appendChild(passwordSpan);
    var passwordInput = document.createElement("input");
    passwordInput.setAttribute("type", "password");
    accountDiv.appendChild(passwordInput);


    var loginButton = document.createElement("button");
    loginButton.innerHTML = "login";
    accountDiv.appendChild(loginButton);

    var msgDiv = document.createElement("div");
    accountDiv.appendChild(msgDiv);

    var data = {
        id: "ternece",
        password: "password"
    };

    loginButton.onclick = function () {
        alert("Do login here")
        var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance
        var theUrl = "http://54.172.190.202:8080/login";
        xmlhttp.open("POST", theUrl);
        xmlhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        xmlhttp.send(JSON.stringify(data));
        console.log(data);
   };  // onclick function

    return accountDiv;
}