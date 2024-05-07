let ctrl = false;
let textArea;
let saveText;
let disappearCount;
let title
let dateId;
let setDate;

// TODO condense the Javascript code, appears several times
// Ctrl + s feature
window.addEventListener('load', function() {
    textArea = document.querySelector('#textInput');
    saveText = document.querySelector('#saveText');
    dateId = new URLSearchParams(window.location.search).get('id');
    title = document.querySelector('#dateTitle');
    setDate = document.querySelector('#setDate');
    disappearCount = 0;

    textArea.addEventListener('keydown',function (e) {
        if (e.keyCode === 17) {
            e.preventDefault();
            ctrl = true;
        }

        if (e.keyCode === 83 && ctrl) {
            e.preventDefault();
            date(textArea.value);
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


})
const date = async () => {
    saveText.innerHTML = "";
    if (title.value.trim().length == 0) {
        saveText.className = "text-danger";
        saveText.innerHTML = "Please set a title!"
        disappearCount = 3;
        return;
    }

    if (setDate.value == null) {
        saveText.className = "text-danger";
        saveText.innerHTML = "Please set a date!";
        disappearCount = 3
        return;
    }

    let bodyData = JSON.stringify({
        'textContent': `${textArea.value}`,
        'id': `${dateId}`,
        'dateTitle': `${title.value}`,
        'date': `${setDate.value}`
    });

    let header = {'Content-Type': 'application/json'}

    const reply = fetch('dateEditor', {
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
        })



}