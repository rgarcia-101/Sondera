let ctrl = false;
let textArea;
let saveText;
let disappearCount;
let noteTitle
let noteId;

// Ctrl + s feature
window.addEventListener('load', function() {
    textArea = document.querySelector('#textInput');
    saveText = document.querySelector('#saveText');
    noteId = new URLSearchParams(window.location.search).get('id');
    noteTitle = document.querySelector('#noteTitle');
    disappearCount = 0;
    textArea.addEventListener('keydown',function (e) {
        if (e.keyCode === 17) {
            e.preventDefault();
            ctrl = true;
        }

        if (e.keyCode === 83 && ctrl) {
            e.preventDefault();
            let content = document.querySelector("#textInput");
            note(content.value);
        }
    })

    textArea.addEventListener('keyup', function(e){
        if (disappearCount > 0) {
            disappearCount--;
        } else {
            saveText.innerHTML = "";
        }
        if (e.keyCode === 17) {
            e.preventDefault();
            ctrl = false;
        }
    });

    textArea.addEventListener('keyup', function(e){

    });

})

const note = () => {
    // TODO enforce limit on title (25 as of now)
    let xhr = new XMLHttpRequest();
    let url = "noteEditor";
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    let sentData = JSON.stringify({"textContent": `${textArea.value}`, "id": `${noteId}`,"noteTitle": `${noteTitle.value}`});
    xhr.send(sentData);
    saveText.innerHTML = "Saved!";
    disappearCount = 10;
}