function home () {

    var content = `
      <h2>Welcome to Sneaker Inventory!</h2>
            
            <div class="row">
                <div>
                    <img src='pics/sneaker-shelf.jpg' alt='sneakers'>
                </div>    
            </div>
            
            <div class="row">
                <div class='column column66'>
                Sneaker Inventory is perfect solution for your sneaker collection management.  <br>
                </div>
                <div class='column column33'>
                    
                </div>
                
                                
            </div>
    `;


    var ele = document.createElement("div");
    ele.innerHTML = content;
    return ele;
}