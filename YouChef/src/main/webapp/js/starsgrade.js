function StarComment(eleID) {
    var starValue = 1;
    var tar = $("#"+ eleID)[0];
    var dealMousemove=function(e) {
        var that = e;
        console.log(e.offsetX);
        starValue = parseInt(e.offsetX / 15) + 1;
        tar.className = "star-s s" + starValue.toString();
        console.log(tar.className);
    }
    $(tar).bind("mousemove", function (e) {
        dealMousemove(e);
    });
    $(tar).bind("click", function () {
        $(tar).unbind("mousemove")
    });
    
    this.getStarValue=function(){
        return starValue;
    }
}

var starQuality = new StarComment("quality");
var starService = new StarComment("service");