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

    loginButton.onclick = function () {
            alert("Do login here")
   };  // onclick function

    return accountDiv;
}