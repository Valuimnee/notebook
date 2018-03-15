window.onload = function () {
    document.getElementById("login").oninput = function (ev) {
        ev.target.setCustomValidity("");
        if (!ev.target.validity.valid) {
            ev.target.setCustomValidity("Login must contain only letters and 0-9_.- and have length from 4 to 20");
        }
    };
    document.getElementById("password").oninput = function (ev) {
        ev.target.setCustomValidity("");
        if (!ev.target.validity.valid) {
            ev.target.setCustomValidity("Password should be from 8 to 40 characters length and may not contain spaces");
        }
    };
    document.getElementById("password").onfocus = function (ev) {
        if(ev.target.validity.valid){
            ev.target.setCustomValiduty("");
        }
    };
    document.getElementById("password").onfocus = function (ev) {
        if(!ev.target.validity.valid&&!ev.target.validity.validationMessage=="Passwords must match!"){
            ev.target.setCustomValidity("Passwords must match!");
        }
    };
    document.getElementById("password2").oninput = function (ev) {
        ev.target.setCustomValidity("");
        if (!ev.target.validity.valid) {
            ev.target.setCustomValidity("Password should be from 8 to 40 characters length and may not contain spaces");
        }
    };
    document.forms["register-form"].addEventListener('submit', function (e) {
        if (!(document.getElementById("password").value == document.getElementById("password2").value)) {
            document.getElementById("password").setCustomValidity("Passwords must match!");
            document.getElementById("password").validity = false;
            document.getElementById("password").focus();
            e.preventDefault();
        }
    }, false);

};