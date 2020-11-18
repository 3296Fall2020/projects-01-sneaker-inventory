function makeClickSort (params) {

    var list = params.list || {};
    var sortIcon = params.sortIcon || {};
    var sortOrderPropName = params.sortOrderPropName || {};
    var title = params.title || {};

    function jsSort(list, byProperty) {


        list.sort(// takes in one parameter -- a function that compares two elements in the list...

            function (q, z) {  // in line anonymous fn to compare list elements:
                // return positive (if first bigger), 0 if equal, negative otherwise.

                var qVal = convert(q[byProperty]);
                var zVal = convert(z[byProperty]);

                var c = 0;
                if (qVal > zVal) {
                    c = 1;
                } else if (qVal < zVal) {
                    c = -1;
                }
                console.log("comparing " + qVal + " to " + zVal + " is " + c);
                return c;
            } // end of the anonymous comparison function
        );

        // check the string to see what type it is, then return that string converted to the right type
        // so as to get the sort order correct.
        function convert(s) {

            if (!s || s.length === 0) {
                //console.log("s is null or empty string");
                return -1;
            }

            // a string that holds a date returns true for isNaN(strDate) (it's not a number)
            // And it returns false for isNaN(Date.parse(strDate))
            var parsedDate = Date.parse(s);
            if (isNaN(s) && !isNaN(parsedDate)) {
                //console.log(s + " is a Date ");
                return parsedDate;
            } else {
                var tmp = s;
                console.log("tmp is " + tmp);
                tmp = tmp.replace("$", ""); // remove dollar signs
                tmp = tmp.replace(",", ""); // remove commas
                if (isNaN(tmp)) { // if not a number, return what was passed in
                    //console.log(s + " is a string - convert to uppercase for sorting purposes");
                    return s.toUpperCase();
                } else {
                    //console.log(tmp + " is a number");
                    return Number(tmp);
                }
            }
        } // convert

    } // jsSort

    function jsSortReverse(list, byProperty) {


        list.sort(// takes in one parameter -- a function that compares two elements in the list...

            function (q, z) {  // in line anonymous fn to compare list elements:

                var qVal = convert(q[byProperty]);
                var zVal = convert(z[byProperty]);

                var c = 0;
                if (qVal < zVal) {
                    c = 1;
                } else if (qVal > zVal) {
                    c = -1;
                }
                console.log("comparing " + qVal + " to " + zVal + " is " + c);
                return c;
            } // end of the anonymous comparison function
        );

        // check the string to see what type it is, then return that string converted to the right type
        // so as to get the sort order correct.
        function convert(s) {

            if (!s || s.length === 0) {
                //console.log("s is null or empty string");
                return -1;
            }

            // a string that holds a date returns true for isNaN(strDate) (it's not a number)
            // And it returns false for isNaN(Date.parse(strDate))
            var parsedDate = Date.parse(s);
            if (isNaN(s) && !isNaN(parsedDate)) {
                //console.log(s + " is a Date ");
                return parsedDate;
            } else {
                var tmp = s;
                console.log("tmp is " + tmp);
                tmp = tmp.replace("$", ""); // remove dollar signs
                tmp = tmp.replace(",", ""); // remove commas
                if (isNaN(tmp)) { // if not a number, return what was passed in
                    //console.log(s + " is a string - convert to uppercase for sorting purposes");
                    return s.toUpperCase();
                } else {
                    //console.log(tmp + " is a number");
                    return Number(tmp);
                }
            }
        } // convert

    } // jsSort

    function addToRow(eleType, row, data, align) {
        var ele = document.createElement(eleType);
        ele.innerHTML = data;
        ele.style.textAlign = align;
        row.appendChild(ele);
        return ele;  // future code may need a reference to this dom object
    }

    function alignment(val) {

        // check if date
        var parsedDate = Date.parse(val);
        if (isNaN(val) && (!isNaN(parsedDate))) {
            return "center";
        }

        // check if image
        if (val.includes(".png") || val.includes(".jpg")) {
            console.log('is center');
            return "center";
        }

        // check if numeric (remove $ and , and then check if numeric)
        var possibleNum = val.replace("$", "");
        possibleNum = possibleNum.replace(",", "");
        if (isNaN(possibleNum)) {
            console.log("not a num - left");
            return "left";
        }

        return "right"; // it's a number

    } // alignment

    function prettyColumnHeading(propName) {

        if (propName.length === 0) {
            return "";
        }

        // capitalize first letter
        var newHdg = propName.charAt(0).toUpperCase();
        // iterate through all characters, inserting space before any capital letters.
        for (var i = 1; i < propName.length; i++) {
            if (propName.charAt(i) < "a") {
                newHdg += " ";
            }
            newHdg += propName.charAt(i);
        }

        return newHdg;
    } // prettyColumnHeading

    function addTableHead (table, list) {

        // Create a thead element, place it in table, then
        // fill up the thead with td elements that are column headers
        // (populated by the field names from the first object in list).
        var tableHead = document.createElement("thead");
        table.appendChild(tableHead);

        var tableHeadRow = document.createElement("tr");
        tableHead.appendChild(tableHeadRow);

        // create one column header per property - column header will show the property name.
        var obj = list[0];
        for (var prop in obj) {

            console.log("setting the sort onclick for column " + prop);
            var iconProp ="<img src = '" + sortIcon + "'/>" + prettyColumnHeading(prop);
            var colHead = addToRow("th", tableHeadRow, iconProp, alignment(obj[prop]));

            // place the property name right into the DOM element that is the "th"
            // Because later when the "th" is clicked "prop" will be the last property
            // (cause this for loop would have already completed).
            colHead.sortPropName = prop;
            colHead.onclick = function () {

                console.log("ready to sort by " + this.sortPropName);

                addTableBody(table, list, this.sortPropName);
            };
        }
    }


    // sort 'list' by 'sortOrderPropName', remove the tbody from 'table' (if there is one),
    // then build a new tbody (from the sorted list) and insert that tbody into the table.
    function addTableBody (table, list, sortOrderPropName) {

        // remove old tbody element if there is one (else you'll get sorted rows added to end of what's there).
        var oldBody = table.getElementsByTagName("tbody");
        if (oldBody[0]) {
            console.log("ready to remove oldBody");
            table.removeChild(oldBody[0]);
        }

        if(table.forward){

            jsSortReverse(list, sortOrderPropName);
            table.forward = false;
        }else if(!table.forward){

            jsSort(list, sortOrderPropName);
            table.forward = true;
        }

        //jsSort(list, sortOrderPropName);

        // Add one row (to HTML table) per element in the array.
        // Each array element has a list of properties that will become
        // td elements (Table Data, a cell) in the HTML table.
        var tableBody = document.createElement("tbody");
        table.appendChild(tableBody);

        for (var i in list) {
            var tableRow = document.createElement("tr");
            tableBody.appendChild(tableRow);

            // create one table data <td> content matching the property name
            var obj = list[i];
            for (var prop in obj) {
                addToRow("td", tableRow, obj[prop], alignment(obj[prop]));
            }
        }

    } // addTableBody

    // return true if any property of obj contains searchKey. Else return false.
    function isToShow(obj, searchKey) {
        if (!searchKey || searchKey.length === 0) {
            return true; // show the object if searchKey is empty
        }
        var searchKeyUpper = searchKey.toUpperCase();
        for (var prop in obj) {
            var propVal = obj[prop]; // associative array, using property name as if index.
            console.log("checking if " + searchKeyUpper + " is in " + propVal);
            var propValUpper = propVal.toUpperCase();
            if (propValUpper.includes(searchKeyUpper)) {

                // do not say it's a hit if it's an image tag
                // that can have a really long URL in its src attribute.
                if (!propValUpper.includes("<IMG")) {
                    console.log("yes it is inside");
                    return true;
                }
            }
        }
        console.log("no it is not inside");
        return false;
    } // isToShow

    function RefreshTableBody(filterValue, table, objList) {

        // Add one row (to HTML table) per element in the array.
        // Each array element has a list of properties that will become
        // td elements (Table Data, a cell) in the HTML table.
        var tableBody = document.createElement("tbody");

        for (var i in list) {
            if (isToShow(objList[i], filterValue)) {
                console.log("adding row " + i + " to the HTML table");

                var tableRow = document.createElement("tr");
                tableBody.appendChild(tableRow);

                // create one table data <td> content matching the property name
                var obj = objList[i];
                for (var prop in obj) {
                    addToRow("td", tableRow, obj[prop], alignment(obj[prop]));
                }

            } else {
                console.log("not adding row " + i + " to the HTML table");
            }
        } // for loop

        // remove old tbody element if there is one (else you'll get sorted rows added to end of what's there).
        var oldBody = table.getElementsByTagName("tbody");
        if (oldBody[0]) {
            console.log("ready to remove oldBody");
            table.removeChild(oldBody[0]);
        }
        table.appendChild(tableBody);

    } // RefreshTableBody

    var returnDiv = document.createElement("div");

    var h2 = document.createElement("h2");
    h2.innerHTML = title;
    returnDiv.appendChild(h2);
    var filterTitle = document.createElement("h5");
    h2.appendChild(filterTitle);
    var searchInput = document.createElement("input");
    returnDiv.appendChild(searchInput);

    returnDiv.classList.add("clickSort");

    var newTable = document.createElement("table");
    returnDiv.appendChild(newTable);

    addTableHead (newTable, list);
    addTableBody (newTable, list, sortOrderPropName);

    RefreshTableBody(searchInput.value, newTable, list);

    searchInput.onkeyup = function () {
        console.log("search filter changed to " + searchInput.value);
        RefreshTableBody(searchInput.value, newTable, list);
    };

    // this can be injected into the content area by other code
    return returnDiv;
}