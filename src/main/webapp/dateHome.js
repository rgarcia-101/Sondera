let button;
let year;
let saveText;

const init = () => {
    button = document.querySelector('#importButton');
    year = document.querySelector('#calendarYear');
    saveText = document.querySelector('#saveText');
    button.addEventListener('click', importHoliday);
}

const importHoliday = async () => {
    console.log("IMPORT received");
    console.log(`${year.value}`);

    let bodyData = JSON.stringify({
        "year": `${year.value}`
    });
    let header = {'Content-Type': 'application/json'}
    const reply = fetch('getHolidays', {
        method: 'PUT',
        body: bodyData,
        headers: header
    }).then(response => response.json())
        .then(received => {
            if (received.responseCode == "0") {
                location.reload();
            } else {
                saveText.className = "text-danger";
                saveText.innerHTML = "Could not import! Please try again."
            }
        })
}


window.onload = init;