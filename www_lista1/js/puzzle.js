var puzzleImage = document.getElementById("puzzleImage");
var newGameButton = document.getElementById("buttonNewGame");
var sliderSelectSize = document.getElementById("sliderSelectSize");
var puzzleSize = document.getElementById("puzzleSize");


var nSize;                                                                      // liczba linii
var nBlocks;                                                                     // liczba wszystkich klockó
var EMPTY_SPACE;                                                                // obiekt zerowego klocka
var blockHeight, blockWidth;                                                      // rozmiary w pikselach jednego klocka
var blockImage;                                                                  // tablica z poszczególnymi obrazkami
var blocks;                                                                      // tablica z poszczególnymi klockami
var gameStarted;                                                                // zmienna od wystartowania gry
var Empty_Position;
var whenMobile = false, canvas, gctx;


function main() {

                                                                                //nałożeneie eventlistenerów
    canvas.addEventListener("click", playerMove);                               //na clic zrób ruch
    canvas.addEventListener("mousemove", mouseOnHover);                           //na najechanie zrób hover
    sliderSelectSize.addEventListener("change", changeSize);               //zmiana rozmiaru
    newGameButton.addEventListener("click", startNewGame);                 //rozpocznij nową grę

                                                                                //deklaracja globalnych zmiennych
    gameStarted = false;
    nSize = parseInt(sliderSelectSize.value);
    puzzleSize.innerHTML = nSize + " na " + nSize;
}


function startNewGame() {                                                       //stwórz nową grę
    gameStarted = true;
    makeBoard();
    shuffleBlocks();                                                             //pomieszaj klocki
}


function changeSize() {                                                         //odczytaj i ustaw size
    nSize = parseInt(sliderSelectSize.value);
    puzzleSize.innerHTML = nSize + " na " + nSize;
    gameStarted = false;                                                        // zatrzymaj grę
    makeBoard();                                                          //zrób nową tablicę
}

function mouseOnHover(event) {
    mouseOutHover()                                                               //usuń poprzednie najechanie
    if(gameStarted) {
        let block =  getSelectedBlock(event);                                     //numer klocka
        var rect, x, y;
        rect = canvas.getBoundingClientRect();                                  //rozmiar oraz położenie klocka
        x = Math.floor((event.clientX - rect.left) / blockWidth);
        y = Math.floor((event.clientY - rect.top) / blockHeight);

        if (!whenMobile) {                                                        //jeślli nie mobile to zrób najechanie
            showHover(block, x, y, rect)
        }

    }

}

function mouseOutHover(){
    const elements = document.getElementsByClassName("hoverElement");

    while (elements.length > 0) elements[0].remove();                           //jeżeli istnieje drugi najechany element to usuń ten pierwszy
}

function showHover(block, x, y, rect){
    let isNeighbour = false
    let width = blockImage[block].width
    let height = blockImage[block].height
    if(block >= nSize && blocks[block - nSize] === EMPTY_SPACE) {                  //sprawdzanie czy jest to sąsiad pustego
        isNeighbour = true
    } else if(block % nSize > 0 && blocks[block - 1] === EMPTY_SPACE) {
        isNeighbour = true
    } else if(block % nSize < nSize - 1 && blocks[block + 1] === EMPTY_SPACE) {
        isNeighbour = true
    } else if(block < nSize * (nSize - 1) &&
        blocks[block + nSize] === EMPTY_SPACE) {
        isNeighbour = true
    }else{
        isNeighbour = false
    }
    if(isNeighbour === true){                                                   //jeśli jest to pobaw się stylami
        var tableRectDiv = document.createElement('div');               //stwórz nowego diva
        tableRectDiv.className = "hoverElement";
        tableRectDiv.style.position = 'absolute';
        tableRectDiv.style.background = 'rgba(239,5,5,0.38)';
        tableRectDiv.style.margin = tableRectDiv.style.padding = '0';
        tableRectDiv.style.top = (rect.top + y*blockHeight ) + 'px';
        tableRectDiv.style.left = (rect.left + x * blockWidth ) + 'px';
        tableRectDiv.style.width = width + 'px';
        tableRectDiv.style.height = height + 'px';
        tableRectDiv.addEventListener("click", playerMove);
        document.body.appendChild(tableRectDiv);
    }

}


function playerMove(event) {
    mouseOutHover()
    if(gameStarted) {                                                            //jeśli gra się rozpoczęła to wymień klocki
        exchangeBlocks(getSelectedBlock(event));                                   //wymień klocek z wybranym
    }
}


function makeBoard() {

    var i, j;

    nBlocks = nSize * nSize;                                                        //obliczanie wielkości tablicy
    EMPTY_SPACE = nBlocks - 1;
    blockHeight = Math.floor(canvas.height / nSize);                             //wysokość i szerokość tablicy
    blockWidth = Math.floor(canvas.width / nSize);


    blocks = [];
    for(i = 0; i < nBlocks; i++) {                                                  //wypełnianie tablicy blocks rosnącymi liczbami
        blocks.push(i);
    }

    loadImage();
    blockImage = [];

    for(i = 0; i < nSize; i++) {                                                   //podział tablicy na odpowiednią ilość kwadratów odpowiedniej wielkości
        for(j = 0; j < nSize; j++) {
            blockImage[nSize * i + j] = gctx.getImageData(j * blockWidth,
                i * blockHeight, blockWidth, blockHeight);
        }
    }
}

function shuffleBlocks() {                                                           //tasowanie klocków https://webdocs.cs.ualberta.ca/~hayward/396/jem/tile.html
    var i, j;
    var emptyRow, inversions;


    gctx.clearRect((nSize - 1) * blockWidth, (nSize - 1) * blockHeight,         //wyczyść rect
        blockWidth, blockHeight);

    blockImage[nBlocks - 1] = gctx.getImageData((nSize - 1) * blockWidth,          //załaduj podzielony obrazek do tablicy
        (nSize - 1) * blockHeight, blockWidth, blockHeight);

    shuffleArray(blocks);                                                            //potasuj tablicę

    inversions = 0;                                                                 //Tasujemy tak aby układ był rozwiązywalny
    for(i = 0; i < nBlocks; i++) {                                                   //liczymy inwersje
        for(j = i + 1; j < nBlocks; j++) {
            if(blocks[i] > blocks[j] && blocks[i] !== EMPTY_SPACE) {
                inversions++;
            }
        }
    }
    if(nSize % 2 === 1) {                                                           //jeśli mamy nieparzystą liczbę kolumn
        if(inversions % 2 === 1) {                                                   //oraz nieparzystą liczbę inwersji
            changeParity();                                                         //trzeba zmienić parzystość
        }
    } else {                                                                        //jeśli mamy parzystą liczbę kolumn
        for(i = 0; i < nBlocks; i++) {                                               //sprawdzamy w którym wierszu jest pusty klocek
            if(blocks[i] === EMPTY_SPACE) {
                emptyRow = Math.floor(i/nSize) + 1;
            }
        }
        if((inversions + nSize - emptyRow) % 2 === 1) {                             //jeśli pusty jest nieparzystym wierszu od dołu
            changeParity();                                                          //trzeba zmienić parzystość
        }
    }
    loadImageOnBlock()
}

function setEmptyBlock() {                                                           //ustaw pusty klocek w rogu
    for(Empty_Position-1;  Empty_Position>=0; Empty_Position--){
        exchangeBlocks(Empty_Position)
    }

}

function exchangeBlocks(block) {                                                      //zamiana klocków z pustą lokalizacją
    var emptyLocation;

    if(block >= nSize && blocks[block - nSize] === EMPTY_SPACE) {
        emptyLocation = block - nSize;
    } else if(block % nSize > 0 && blocks[block - 1] === EMPTY_SPACE) {
        emptyLocation = block - 1;
    } else if(block % nSize < nSize - 1 && blocks[block + 1] === EMPTY_SPACE) {
        emptyLocation = block + 1;
    } else if(block < nSize * (nSize - 1) &&
        blocks[block + nSize] === EMPTY_SPACE) {
        emptyLocation = block + nSize;
    } else {
        return;
    }


    blocks[emptyLocation] = blocks[block];                                             //zamiana backend
    blocks[block] = EMPTY_SPACE;


    gctx.putImageData(blockImage[blocks[emptyLocation]],                               //zamiana frontend
        (emptyLocation % nSize) * blockWidth,
        Math.floor(emptyLocation / nSize) * blockHeight);
    gctx.putImageData(blockImage[blocks[block]],
        (block % nSize) * blockWidth,
        Math.floor(block / nSize) * blockHeight);

    if(isSolved()) {                                                                //czy konec
        setTimeout(startNewGame,3000);
    }
}

function getSelectedBlock(event) {                                                   //metoda ta zwraca numer wybranego recta z tablicy
    var rect, x, y;
    rect = canvas.getBoundingClientRect();
    x = Math.floor((event.clientX - rect.left) / blockWidth);
    y = Math.floor((event.clientY - rect.top) / blockHeight);
    return x + nSize * y;
}

function isSolved() {                                                               //sprawdzenie czy wszystkie klocki są w odpowiedniej kolejnści
    var i;

    for(i = 0; i < nBlocks - 1; i++) {
        if(blocks[i] > blocks[i + 1]) {
            return false;
        }
    }
    return true;
}

function shuffleArray(array) {                                                       //tasowanie tablicy z podzielonym obrazkiem w randomowy sposób
    var i, j, temp;

    for(i = array.length - 1; i > 0; i--) {
        j = Math.floor(Math.random() * (i + 1));
        temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    return array;
}

function changeParity() {                                                             //zmiana parzystości (zamieniamy zerowy klocek)  po to by układ był rozwiązywalny

    var temp;

    if(blocks[0] === EMPTY_SPACE || blocks[1] === EMPTY_SPACE) {
        temp = blocks[nBlocks - 1];
        blocks[nBlocks - 1] = blocks[nBlocks - 2];
        blocks[nBlocks - 2] = temp;
    } else {
        temp = blocks[0];
        blocks[0] = blocks[1];
        blocks[1] = temp;
    }
}


function loadImageOnBlock() {                                                            //załaduj już pomieszane klocki
    var i, j;

    if(!gameStarted) {
        loadImage();
    } else {
        for (i = 0; i < nSize; i++) {
            for(j = 0; j < nSize; j++) {
                if(blocks[nSize * i + j]=== EMPTY_SPACE){                               //znajdź lokalizację pustego klocka
                    Empty_Position = nSize * i + j
                }
                gctx.putImageData(blockImage[blocks[nSize * i + j]],
                    j * blockWidth, i * blockHeight);
            }
        }
        setEmptyBlock()                                                                //przesuń pusty klocek do rogu
    }
}

function loadImage() {                                                                //wsadzenie obrazka do puzzleImage, staart 00, wysokość i szerokość
    gctx.drawImage(puzzleImage, 0, 0, canvas.width, canvas.height);
}

function getImage(url) {                                                              //zaciągnij wybrany obrazek, obsłuż błędy
    return new Promise(
        function (resolve, reject) {
            var img = new Image();
            img.onload = function () {
                resolve(url);
            };
            img.onerror = function () {
                reject(url);
            };
            img.src = url;
        });
}

function whenSuccess(url) {                                                             //obietnice on succes i on faliture
    document.getElementById("puzzleImage").src = url;
    makeBoard()                                                                 //na sukces załaduj obraz i initializuj tablicę
}

function whenFailure(url) {
    console.error("Error loading " + url);
}

function loadFull(image) {
    gameStarted = false;
    var promise = getImage(image);
    promise.then(whenSuccess).catch(whenFailure);
}

window.onload = () =>                                                                 //działa tak samo jak linika niżej
 {                                                                                    //zwraca info gdy strona jest w pełni załadowana
    whenMobile = /iPhone|iPad|iPod|Android/i.test(navigator.userAgent);                 //zwraca string o aktualnej przeglądarce
    if(whenMobile === true){
        canvas = document.getElementById("gameCanvasMobile");
        gctx = canvas.getContext("2d");
    }else{
        canvas = document.getElementById("gameCanvas");
        gctx = canvas.getContext("2d");
    }
    main()

     loadFull('../img/organy1.jpg')
                                                                                      //nałożenie event listenera na każdy button (na wszystkie obrazki)
    document.querySelectorAll("button").forEach((button, i) => {
        button.addEventListener("click", () => loadFull(`../img/organy${i + 1}.jpg`)); //załądowanie wybranego
    });

};