		var flag = false;
        var starAry;
document.addEventListener("DOMContentLoaded",function () {
            var imgs = document.getElementsByTagName("img");
            starAry = new Array([imgs.length]);
            for (var i = 0; i < imgs.length; i++) {
                starAry[i] = imgs[i].id;
                imgs[i].addEventListener("mouseover",function () { mouseOver(this.id);});
                imgs[i].addEventListener("click",function () { Click(this.id);});
            }
        });

function mouseOver(imgid) {
      if (!flag) {
                var index = starAry.indexOf(imgid);
                var fox = (index+1);
                for (var i = 0; i <= index; i++) {
                    document.getElementById(starAry[i]).src = "../images/stars/starson.png";
                }
                if (index < starAry.length - 1) {
                    for (var i = index + 1; i < starAry.length; i++) {
                        document.getElementById(starAry[i]).src = "../images/stars/starsoff.png";
                    }
                }
                document.getElementById("rate").innerHTML = "評分中..." + fox;
            }
        }

function Click(imgid) {
            if (!flag) {
                flag = true;
                var index = starAry.indexOf(imgid);
                var fox = (index+1);
                var getStar_element = document.getElementById("stars");
                getStar_element.value=fox;
                document.getElementById("rate").innerHTML = "選定了"+fox + "顆星";
            }     
        }
