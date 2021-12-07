function Point(x, y, z) {
    this.x = x;
    this.y = y;
    this.z = z;
}
 
function clonePoint(ptr) {
    this.x = ptr.x;
    this.y = ptr.y;
    this.z = ptr.z;
}
 
var canvas;
var ctx;
var pointField = [];
var CenterOfCoords = new Point(750, 380, 0);
var moveByOfCase = [];
var startPointOfCase = [];
var endPointOfCase = [];
var expandDigre = 30;
var horizontBuffHeight = [];
var horizontBuffLow = [];
var horizontBuffLeft =[];
var horizontBuffRight = [];
 
var InGradusAngles = {
    x: 0,
    y: 0,
    z: 0
};
 
var angles = {
    x: 0,
    y: 0,
    z: 0
};
 
function makeRealAngle() {
    angles.x = InGradusAngles.x / 180 * Math.PI;
    angles.y = InGradusAngles.y / 180 * Math.PI;
    angles.z = InGradusAngles.z / 180 * Math.PI;
}
 
function fillHorizontBuffs() {
    for(let i = 0; i <= canvas.height; i += 1) {
        horizontBuffHeight[i] = 0;
        horizontBuffLow[i] = canvas.height;
        horizontBuffLeft[i] = canvas.width;
        horizontBuffRight[i] = 0;
    }
 
}
 
function rotatePointByX(Pnt) {
    var tmp = new clonePoint(Pnt);
    Pnt.y = Math.cos(angles.x) * tmp.y + Math.sin(angles.x) * tmp.z;
    Pnt.z = -Math.sin(angles.x) * tmp.y + Math.cos(angles.x) * tmp.z;
}


function rotatePointByY(Pnt) {
    var tmp = new clonePoint(Pnt);
    Pnt.x = Math.cos(angles.y) * tmp.x - Math.sin(angles.y) * tmp.z;
    Pnt.z = Math.sin(angles.y) * tmp.x + Math.cos(angles.y) * tmp.z;
}
 
function rotatePointByZ(Pnt) {
    var tmp = new clonePoint(Pnt);
    Pnt.x = Math.cos(angles.z) * tmp.x + Math.sin(angles.z) * tmp.y;
    Pnt.y = -Math.sin(angles.z) * tmp.x + Math.cos(angles.z) * tmp.y;   
}
 
function MovePointToNewCenter(Pnt) {
    Pnt.x += CenterOfCoords.x;
    Pnt.y += CenterOfCoords.y;
    Pnt.z += CenterOfCoords.z;
}
 
function expandPoint(Pnt) {
 
    Pnt.x *= expandDigre;
    Pnt.y *= expandDigre;
    Pnt.z *= expandDigre;
 
}
 
function fullRotation(Pnt) {
    rotatePointByX(Pnt);
    rotatePointByY(Pnt);
    rotatePointByZ(Pnt);
}
 
function doNesRotationAndMovesAndInvertY(Pnt, show) {
    var tmp = new clonePoint(Pnt);
    tmp.y = -tmp.y;
    rotatePointByX(tmp);
    rotatePointByY(tmp);
    rotatePointByZ(tmp);
    expandPoint(tmp);
    MovePointToNewCenter(tmp);
 
    return new clonePoint(tmp);
}
 
function isPointCorrect(Pnt, isFirst, prevPnt) {
    var pp = Pnt.x !== NaN && Pnt.y !== NaN && Pnt.z !== NaN  ? true : false;
    var correct = false;
    var x = Math.round(Pnt.x);
    var y = Pnt.y;
 
    if(horizontBuffHeight[x] < y) {
        correct = true;
        horizontBuffHeight[x] = y;
    }
 
    if(horizontBuffLow[x] > y) {
        correct = true;
        horizontBuffLow[x] = y;
    }
 
    y = Math.round(Pnt.y);
    x = Pnt.x;
 
    if(horizontBuffLeft[y] > x) {
        horizontBuffLeft[y] = x;
        correct = true;
    }
 
    if(horizontBuffRight[y] < x) {
        horizontBuffRight[y] = x;
         correct = true;
    }
 
    if(correct) {
        if(isFirst === false) {
            let dx = (-Pnt.x + prevPnt.x) / 20;
            let dy = (-Pnt.y + prevPnt.y) / 20;
            x = Pnt.x;
            y = Pnt.y;
            while(Math.abs(x - prevPnt.x) > 1e-6 && Math.abs(y - prevPnt.y) > 1e-6) {
                if(horizontBuffHeight[Math.round(x)] < y) {
                    horizontBuffHeight[Math.round(x)] = y;
                }
 
                if(horizontBuffLow[Math.round(x)] > y) {
                    horizontBuffLow[Math.round(x)] = y;
                }
 
                if(horizontBuffLeft[Math.round(y)] > x) {
                    horizontBuffLeft[Math.round(y)] = x;
                }
 
                if(horizontBuffRight[Math.round(y)] < x) {
                    horizontBuffRight[Math.round(y)] = x;
                }
 
                x += dx;
                y += dy;
            }
        }
    }
 
return pp && correct;
}
 
function drawPoint(Pnt) {
    var halfSize = 5;
    ctx.fillRect(Math.round(Pnt.x) - halfSize, Math.round(Pnt.y) - halfSize, 2 + halfSize, 2 + halfSize);
 
}
 
function drawLines(Pnt) {
    ctx.lineTo(Pnt.x, Pnt. y);
}
 
//Функция вида sin(x) * cos(y)
function fillPointField() {
    var i = 0, j = 0; 
 
    for (let x = -7; x <= 7; x += 0.1, i += 1) {
        j = 0;
        let tmp = [];
         let total;
        for(let y = -4; y <= 4; y += 0.1, j += 1) {
            tmp[j] = new Point(x, y, Math.sin(x) * Math.cos(y) );
        }
        pointField.push(tmp);
    }
}
 
 
function whicIsCloser() {
	var z = doNesRotationAndMovesAndInvertY(pointField[0][0]).z;
	var cnt = 0;
	var a, b, tmp;
 	a = 0;
	b = pointField[0].length - 1;
	tmp = doNesRotationAndMovesAndInvertY(pointField[a][b]);
	if(z < tmp.z) {
		z = tmp.z;
		cnt = 1;
	}
	a = pointField.length - 1;
	tmp = doNesRotationAndMovesAndInvertY(pointField[a][b]);
	if(z < tmp.z) {
		z = tmp.z;
		cnt = 3;
	}
	a = pointField.length - 1;
	b = 0;
	tmp = doNesRotationAndMovesAndInvertY(pointField[a][b]);
	if(z < tmp.z) {
		z = tmp.z;
		cnt = 2;
	}
	return cnt;
}
 
 
function drawGrafik() {
 	var Case = whicIsCloser(); 
 	var start = startPointOfCase[Case];
 	var end = endPointOfCase[Case];
 	var moveBy = moveByOfCase[Case];
 
 	fillHorizontBuffs();
 
 	for(let i = start.x; i != end.x; i += moveBy.x) {
 
 		ctx.beginPath();
 		var prev = new clonePoint(start);
 		var isFirst = true;
 
 		for(let j = start.y; j != end.y; j += moveBy.y) {
 
 			var tmp = new clonePoint(pointField[i][j]);
 			var point = doNesRotationAndMovesAndInvertY(tmp);
 
 			if(i % 4 % 3 === 0)
 				ctx.strokeStyle = "red";
 			else
 				ctx.strokeStyle = "blue";
 
 			if(i === start.x) {
 				ctx.strokeStyle = "yellow";
 
 			}
 
 			if(isFirst) {
 				ctx.moveTo(point.x, point.y);
 				prev = point;
                isFirst = false;
 			}
 
 			if(isPointCorrect(point, isFirst, prev) === true) {
 				ctx.lineTo(point.x, point.y);
 				isFirst = false;
 			} else {
 
 		         ctx.stroke();
 				ctx.closePath();
 				ctx.beginPath();
 			}
 			prev = point;
 		}
 
 		ctx.stroke();
 		ctx.closePath();
 	}
 
}
 
function clearScreen() {
    var tmp = ctx.fillStyle;
    ctx.fillStyle = "black";
    ctx.fillRect(0, 0, canvas.width, canvas.height);
    ctx.fillStyle = tmp;
}
 
var isListening = false;
var mouseStartX, mouseStartY;
 
function addEventsListeners() {
	canvas.addEventListener('mousedown', function(event) {
        mouseStartX = event.pageX;
        mouseStartY = event.pageY;
        isListening = true;
    });
 
    canvas.addEventListener("mouseup", function(event) {
        isListening = false;
    });
 
    canvas.addEventListener("mousemove", function(event) {
        if(isListening === true) {
 
            InGradusAngles.x += (mouseStartY - event.pageY) / 10;
            InGradusAngles.y += (mouseStartX - event.pageX) / 10;
            mouseStartX = event.pageX;
            mouseStartY = event.pageY;
            makeRealAngle();
            clearScreen();
            drawGrafik();
        }
    });
}
 

function preparetions() {
	canvas = document.getElementById("paint");
    ctx = canvas.getContext("2d");
 
 
    fillPointField();
 
    moveByOfCase.push(new Point(1, 1, 0));
    moveByOfCase.push(new Point(1, -1, 0));
    moveByOfCase.push(new Point(-1, 1, 0));
    moveByOfCase.push(new Point(-1, -1, 0));
 
    startPointOfCase.push(new Point(0, 0, 0));
    startPointOfCase.push(new Point(0, pointField[0].length - 1, 0));
    startPointOfCase.push(new Point(pointField.length - 1, 0, 0));
    startPointOfCase.push(new Point(pointField.length - 1, pointField[pointField.length - 1].length - 1, 0));
 
    endPointOfCase.push(new Point(pointField.length - 1, pointField[pointField.length - 1].length - 1, 0));
    endPointOfCase.push(new Point(pointField.length - 1, 0, 0));
    endPointOfCase.push(new Point(0, pointField[0].length - 1, 0));
    endPointOfCase.push(new Point(0, 0, 0));
 
    addEventsListeners();
 
    ctx.fillStyle = "white";
    clearScreen();
 
    makeRealAngle();
}

function setUp() { 
    preparetions();
    drawGrafik();
}