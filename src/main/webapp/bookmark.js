let textArea
let url
let title
let bookmarkId;
const init = () => {
    textArea = document.querySelector('#textInput');
    url = document.querySelector('#urlText');
    title = document.querySelector('#bookmarkTitle');
    bookmarkId = new URLSearchParams(window.location.search).get('id');
}

const bookmark = async () => {

    saveText.innerHTML = "";

    let bodyData = JSON.stringify({
        'description': `${textArea.value}`,
        'id': `${bookmarkId}`,
        'bookmarkTitle': `${title.value}`,
        'link': `${url.value}`
    });

    let header = {'Content-Type': 'application/json'}

    const reply =  fetch('bookmarkEditor', {
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

window.onload = init;