function myfunction(id) {
    var nm = document.getElementById(nmId).value;
    var btn = document.getElementById(id).name;
    if(btn=="button1") {
        alert(nm+", Of Course You can Vote! Cheers");
    }
    else {
        alert("No Voting rights For You Yet," +nm+ "!(:");
    }
}