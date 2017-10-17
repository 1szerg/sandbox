function readAndRun() {
    var nodesCountRead = document.getElementById("nodes-count-input").value;
    var nodesCountInput = Number(nodesCountRead);
    buildGraph(nodesCountInput);
}

function buildGraph(nodesCount) {
    if(!nodesCount){nodesCount = 200;}

    var canvas = document.getElementById("gCanvas");
    var h = canvas.height;
    var w = canvas.width;
    var ctx = canvas.getContext("2d");
    var info = document.getElementById("info");

    //fill back
    ctx.fillStyle = "#f7f3f8";
    ctx.fillRect(0, 0, w, h);

    //create graph with live preview
    var graph = {};
    var conn = {};
    info.innerHTML = "added "+nodesCount+" nodes";
    for (var i = 0; i < nodesCount; i++) {
        var x = Math.random() * w;
        var y = Math.random() * h;
        graph[i] = new Node(x, y);
        if(i == 0) {
            ctx.fillStyle = "#f84aee";
        }else if(i == (nodesCount-1)){
            ctx.fillStyle = "#f84aee";
        }else{
            ctx.fillStyle = "#a2a5a5";
        }
        ctx.fillRect(x - 1, y - 1, 3, 3);
        if (i > 0) {
            var linkedTo = Math.round(Math.random() * (i - 1));
            var cIndex = i-1;
            conn[cIndex] = new Link(graph[i], graph[linkedTo]);
            ctx.strokeStyle = "#e0e0e0";
            ctx.beginPath();
            ctx.moveTo(conn[cIndex].node1.x, conn[cIndex].node1.y);
            ctx.lineTo(conn[cIndex].node2.x, conn[cIndex].node2.y);
            ctx.stroke();
        }
    }

    // find random way from start to finish
    var randpath = {};
    randpath[0] = graph[0];
    var goal = graph[nodesCount - 1];
    var current = graph[0];
    var steps = 0;
    while (steps < nodesCount*10) {
        steps++;
        var next = getRandomNext(conn, current);
        randpath[randpath.length]=next;
        ctx.strokeStyle = "#afb0b1";
        ctx.beginPath();
        ctx.moveTo(current.x, current.y);
        ctx.lineTo(next.x, next.y);
        ctx.stroke();
        if(current == goal){break;}
        current = next;
    }
    if(current == goal){
        info.innerHTML = "found path in "+steps+" steps on "+nodesCount+" nodes";
    }else{
        info.innerHTML = "no path trough "+nodesCount+" nodes in "+steps+" steps";
    }

}

function getRandomNext(conn, current) {
    var n = -1;
    for(var node in conn){n++;}
    while (true) {
        var k = Math.round(Math.random() * n);
        try{if (conn[k].node1 == current) {
            return conn[k].node2;
        }
            if (conn[k].node2 == current) {
                return conn[k].node1;
            }
        }catch (err){
            info.innerHTML = "no element at "+k;
            return null;
        }

    }
}

function Node(x, y) {
    this.x = x;
    this.y = y;
}

function Link(n1, n2) {
    this.node1 = n1;
    this.node2 = n2;
}
