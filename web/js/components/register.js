function register () {

    var accountDiv = document.createElement("div");
    accountDiv.classList.add("account");

    var idSpan = document.createElement('span');
    idSpan.innerHTML = "ID ";
    accountDiv.appendChild(idSpan);
    var idInput = document.createElement("input");
    accountDiv.appendChild(idInput);
    var linebreak = document.createElement("br");
    accountDiv.appendChild(linebreak);

    var passwordSpan = document.createElement('span');
    passwordSpan.innerHTML = "Password ";
    accountDiv.appendChild(passwordSpan);
    var passwordInput = document.createElement("input");
    passwordInput.setAttribute("type", "password");
    accountDiv.appendChild(passwordInput);
    linebreak = document.createElement("br");
    accountDiv.appendChild(linebreak);

    var passwordConfirmSpan = document.createElement('span');
    passwordConfirmSpan.innerHTML = "Confirm Password ";
    accountDiv.appendChild(passwordConfirmSpan);
    var passwordConfirmInput = document.createElement("input");
    passwordConfirmInput.setAttribute("type", "password");
    accountDiv.appendChild(passwordConfirmInput);
    linebreak = document.createElement("br");
    accountDiv.appendChild(linebreak);

    var registerButton = document.createElement("button");
    registerButton.innerHTML = "Register";
    accountDiv.appendChild(registerButton);

    var msgDiv = document.createElement("div");
    accountDiv.appendChild(msgDiv);

    registerButton.onclick = function () {
        if(passwordInput.value !== passwordConfirmInput.value){
            alert("Your password confirm does not match password entered");
        }
        else{
            alert("Do register here")
        }

    };  // onclick function

    return accountDiv;
}