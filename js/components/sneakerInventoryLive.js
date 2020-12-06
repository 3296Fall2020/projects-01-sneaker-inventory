function sneakerInventoryLive() {
    var clickSortContainer = document.createElement("div");
    clickSortContainer.classList.add("clickSort");
    ajaxLive("https://54.172.190.202:443/inventory", processSneakerInventory, clickSortContainer);

    function processSneakerInventory (list) {

        /*
        [{"index":1,"shoeName":"yeezy-black","sku":"fu9006","size":"11","price":"$300","marketValue":"355"}]
         */

        var inventoryList = [];
        for (var i=0; i <list.length; i++) {
            inventoryList[i] = {};
            inventoryList[i].index = list[i].index.toString();
            inventoryList[i].shoeName = list[i].shoeName;
            inventoryList[i].sku = list[i].sku;
            inventoryList[i].size = list[i].size;
            inventoryList[i].price = list[i].price;
            inventoryList[i].marketValue = list[i].marketValue;
            inventoryList[i].update = `<img src=icon/update.png class = "icon" onclick= "window.location.hash = '#/editSneaker'">`;
            inventoryList[i].delete = `<img src=icon/delete.png class = "icon" onclick= "window.location.hash = '#/deleteSneaker'">`;
        }
        console.log(inventoryList);
        var params = {
            list: inventoryList,
            sortOrderPropName: "index",
            sortIcon: "icon/sortUpDown16.png",
            title: "Sneaker Inventory"
        }

        var myClickSort = makeClickSort(params);
        clickSortContainer.appendChild(myClickSort);

        var addSneaker = document.createElement("button");
        addSneaker.innerHTML = "Add Sneaker";
        addSneaker.onclick = function() {window.location.hash = "#/addSneaker"};
        clickSortContainer.appendChild(addSneaker);
    }
    return clickSortContainer;
}