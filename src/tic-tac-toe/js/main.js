function replaceAt(src, index, replacement) {
    return src.substr(0, index) + replacement + src.substr(index + replacement.length);
}

function onTblClick(x, y) {
    if(isGameEnded()){return;}
    var index = indexFromCoords(x, y);
    var data = loadData();
    var selected = data[index];
    if(selected == " "){
        makeTurn(x,y);
    }
}

function makeTurn(x, y) {
    var data = loadData();
    var playerSide = getPlayerSide();
    var index = indexFromCoords(x,y);
    //saved[index] =  playerSide;
    data = replaceAt(data, index, playerSide);
    saveData(data);
    refreshView();
    if(verifyPlayerWon()){
        playerWon();
    } else {
        if(verifyGameEnd()){
            gameEnd();
        }else{
            cpuTurn();
            refreshView();
            if(verifyCpuWon()){
                CpuWon();
            }else{
                if(verifyGameEnd()){
                    gameEnd();
                }
            }
        }
    }
}

function cpuTurn() {
    var data = loadData();
    var cpuSide = getCpuSide();
    if(!findOneClickWin(data, cpuSide)){
        if(!avoidPlayerWinOneClick(data, cpuSide, getPlayerSide())){
            randomMove(data, cpuSide);
        }
    }

}

function findOneClickWin(data, side) {
    for(var i = 0; i < 9; i++){
        if(data[i] == " "){
            var tmpData = replaceAt(data, i, side);
            if(checkSideWins(tmpData,side)){
                saveData(tmpData);
                return true;
            }
        }
    }
    return false;
}

function avoidPlayerWinOneClick(data, mySide, enemySide) {
    for(var i = 0; i < 9; i++) {
        if (data[i] == " ") {
            var tmpData = replaceAt(data, i, enemySide);
            if (checkSideWins(tmpData, enemySide)) {
                data = replaceAt(data, i, mySide);
                saveData(data);
                return true;
            }
        }
    }
    return false;
}

function randomMove(data, cpuSide) {
    if(data[4]==" "){
        data = replaceAt(data, 4, cpuSide);
        saveData(data);
    }else{
        var limit = 1000;
        while(limit-- > 0){
            var pos = Math.round(Math.random() * 9);
            if(data[pos] == " "){
                data = replaceAt(data, pos, cpuSide);
                saveData(data);
                limit=0;
            }
        }
    }
}

function refreshView() {
    var saved = loadData();
    updateTile(1,1,saved[0]);
    updateTile(1,2,saved[1]);
    updateTile(1,3,saved[2]);
    updateTile(2,1,saved[3]);
    updateTile(2,2,saved[4]);
    updateTile(2,3,saved[5]);
    updateTile(3,1,saved[6]);
    updateTile(3,2,saved[7]);
    updateTile(3,3,saved[8]);
}

function updateTile(x, y, value) {
    if(value == "O"){
        document.getElementById("img"+x+y).src = "img/o.png";
    }else if(value == "X"){
        document.getElementById("img"+x+y).src = "img/x.png";
    }else{
        document.getElementById("img"+x+y).src = "img/blank.png";
    }
}

function indexFromCoords(x, y) {
    var index = 3*(Number(x)-1) + (Number(y)-1);
    return index;
}

function loadData() {
    return document.getElementById("savedData").value;
}

function saveData(data) {
    document.getElementById("savedData").value = data;
}

function verifyGameEnd() {
    var data = loadData();
    return data.search(" ") < 0;
}

function gameEnd() {
    debug("Game over");
    startOver();
}

function verifyCpuWon() {
    return checkSideWins(loadData(), getCpuSide());
}

function CpuWon() {
    debug("CPU won");
    var score = Number(document.getElementById("cpu-score").innerHTML);
    score++;
    document.getElementById("cpu-score").innerHTML = score.toString();
    setGameEnded();
    startOver();
}

function verifyPlayerWon() {
    return checkSideWins(loadData(), getPlayerSide());
}

function playerWon() {
    debug("Player won");
    var score = Number(document.getElementById("player-score").innerHTML);
    score++;
    document.getElementById("player-score").innerHTML = score.toString();
    setGameEnded();
    startOver();
}

function startOver() {
    document.getElementById("start-x").disabled = false;
    document.getElementById("start-o").disabled = false;
    document.getElementById("player-x").innerHTML="Select";
    document.getElementById("player-o").innerHTML=" your side";
}

function checkSideWins(data, side) {
    var result = false
        || checkPattern(data, [0, 1, 2], side)
        || checkPattern(data, [3, 4, 5], side)
        || checkPattern(data, [6, 7, 8], side)
        || checkPattern(data, [0, 3, 6], side)
        || checkPattern(data, [1, 4, 7], side)
        || checkPattern(data, [2, 5, 8], side)
        || checkPattern(data, [0, 4, 8], side)
        || checkPattern(data, [2, 4, 6], side);
    return result;
}

function checkPattern(data, pattern, side) {
    for(var i = 0; i < 3; i++){
        if(data[pattern[i]] != side){
            return false;
        }
    }
    return true;
}

function startAsX() {
    startGame("X");
    document.getElementById("player-x").innerHTML = "[X] Player";
    document.getElementById("player-o").innerHTML = "[O] CPU";
}

function startAsO() {
    startGame("O");
    document.getElementById("player-x").innerHTML = "[X] CPU";
    document.getElementById("player-o").innerHTML = "[O] Player";
    cpuTurn();
    refreshView();
}

function debug(msg) {
    var text =  document.getElementById("debug").innerHTML + "<br>" + msg;
    document.getElementById("debug").innerHTML = text;
}

function clearDebug() {
    document.getElementById("debug").innerHTML = "";
}

function getPlayerSide() {
    return loadData()[9];
}

function getCpuSide() {
    return loadData()[9] == "X" ? "O" : "X";
}

function isGameEnded() {
    return loadData()[10] == "2";
}

function setGameEnded() {
    saveData(replaceAt(loadData(),10,"2"));
}

function startGame(playerSide) {
    document.getElementById("start-o").disabled = true;
    document.getElementById("start-x").disabled = true;
    clearDebug();
    saveData("         "+playerSide+"1");//[0..8-field state; 9-player side; 10-game state(1-game started, 2-game ended)]
    refreshView();
}
