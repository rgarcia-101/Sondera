let ctrl = false;
let textArea;
let saveText;
let disappearCount;
let title
let noteId;

// Ctrl + s feature
window.addEventListener('load', function() {
    textArea = document.querySelector('#textInput');
    saveText = document.querySelector('#saveText');
    noteId = new URLSearchParams(window.location.search).get('id');
    title = document.querySelector('#noteTitle');
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
const note = async () => {
    saveText.innerHTML = "";

    if (title.value.trim().length == 0) {
        saveText.className = "text-danger";
        saveText.innerHTML = "Please set a title!"
        disappearCount = 3;
        return;
    }
    let bodyData = JSON.stringify({
        'textContent': `${textArea.value}`,
        'id': `${noteId}`,
        'noteTitle': `${title.value}`
    });

    let header = {'Content-Type': 'application/json'}

    const reply =  fetch('noteEditor', {
        method: 'PUT',
        body: bodyData,
        headers: header
    }).then(response => response.json())
        .then(received => {
            if (received.responseCode == "0") {
                saveText.className = "text-success"
                saveText.innerHTML = "Saved!";
                disappearCount = 10;
            } else {
                saveText.className = "text-danger";
                saveText.innerHTML = "Could not save! Please try again."
                disappearCount = 3;
            }
            console.log(received);
        })



}