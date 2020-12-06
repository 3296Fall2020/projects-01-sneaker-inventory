function addSneaker () {

    var insertDiv = document.createElement("div");
    insertDiv.classList.add("account");

    //create break line element
    var br = document.createElement("br");

    //create a form
    var form = document.createElement("form");
    form.setAttribute("name", "myForm");
    form.onsubmit = function() {

        let myForm = document.forms["myForm"];
        let formData = new FormData(myForm);

        var object = {};
        formData.forEach(function(value, key){
            object[key] = value;
        });
        var json = JSON.stringify(object);

        var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance
        xmlhttp.withCredentials = true;
        var theUrl = "https://54.172.190.202:443/addSneaker";

        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200){
                console.log(this.responseText);
                var result = JSON.parse(xmlhttp.response);
                if (result.sessionID == 'SessionID'){
                    alert("Sneaker Added to Inventory");
                    window.location.hash = "#/sneakerInventory";
                }else{
                    alert(result.sessionID);
                }
            }
        };

        xmlhttp.open("POST", theUrl);
        xmlhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        xmlhttp.send(json);

        return false;
    };

    /*
        needed elements shoeName, sku, size, price, user_id

     */

    //create an input element for Sneaker Name
    var shoeName = document.createElement("input");
    shoeName.setAttribute("type", "text");
    shoeName.setAttribute("name", "shoeName");
    shoeName.setAttribute("placeholder", "Sneaker Name");
    shoeName.setAttribute("required","true");
    shoeName.setAttribute("maxlength","45");

    //create an input element for SKU
    var sku = document.createElement("input");
    sku.setAttribute("type", "text");
    sku.setAttribute("name", "sku");
    sku.setAttribute("placeholder", "SKU");
    sku.setAttribute("required","true");
    sku.setAttribute("maxlength","45");

    //create an input element for Size
    var size = document.createElement("input");
    size.setAttribute("type", "text");
    size.setAttribute("name", "size");
    size.setAttribute("placeholder", "Size");
    size.setAttribute("required","true");
    size.setAttribute("maxlength","15");

    //create an input element for Price
    var price = document.createElement("input");
    price.setAttribute("type", "text");
    price.setAttribute("name", "price");
    price.setAttribute("placeholder", "Price");
    price.setAttribute("required","true");
    price.setAttribute("maxlength","15");

    //create a submit button
    var insertButton = document.createElement("input");
    insertButton.setAttribute("type", "submit");
    insertButton.setAttribute("value", "Add Sneaker");


    form.appendChild(shoeName);
    form.appendChild(br.cloneNode());
    form.appendChild(sku);
    form.appendChild(br.cloneNode());
    form.appendChild(size);
    form.appendChild(br.cloneNode());
    form.appendChild(price);
    form.appendChild(br.cloneNode());
    form.appendChild(insertButton);

    insertDiv.appendChild(form);

    return insertDiv;

}