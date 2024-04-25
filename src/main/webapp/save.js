let ctrl = false;
let textArea;
let saveText;
let disappearCount;
let noteId;
// TODO test this

// Ctrl + s save feature
window.addEventListener('load', function() {
    textArea = document.querySelector('#textInput');
    saveText = document.querySelector(("#saveText"));
    noteId = new URLSearchParams(window.location.search).get('id');
    disappearCount = 0;
    textArea.addEventListener('keydown',function (e) {
        if (e.keyCode === 17) {
            e.preventDefault();
            ctrl = true;
        }

    if (e.keyCode === 83 && ctrl) {
        e.preventDefault();
        let content = document.querySelector("#textInput");
        console.log("Saving...");
        save(content.value);
    }
    })

    textArea.addEventListener('keyup', function(e){
        console.log(textArea.value);
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

const save = () => {
    let xhr = new XMLHttpRequest();
    let url = "notes";
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    let sentData = JSON.stringify({"textContent": `${textArea.value}`,"id": `${noteId}`});
    xhr.send(sentData);
    saveText.innerHTML = "Saved!";
    disappearCount = 10;
}