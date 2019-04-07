function hideInitialData() {
    $('#initialH2').attr("hidden", true);
    $('#choiceH2').attr("hidden", true);
}

function listVirusData() {
    hideInitialData();
    $('#virus-thead').attr("hidden", false);
    $('#capital-thead').attr("hidden", true);
    $('#virusH2').attr("hidden", false);
    $('#capitallH2').attr("hidden", true);
    $('#list-table-body').empty();
    fetchVirusData();
}

function listCapitalData() {
    hideInitialData();
    $('#virus-thead').attr("hidden", true);
    $('#capital-thead').attr("hidden", false);
    $('#virusH2').attr("hidden", true);
    $('#capitallH2').attr("hidden", false);
    $('#list-table-body').empty();
    fetchCapitalData();
}

function fetchVirusData() {
    let admin = 'ADMIN';
    let moderator = 'MODERATOR';
    fetch('/virus/fetch') // Fetch the data (GET request)
        .then((response) => response.json()) // Extract the JSON from the Response
        .then((json) => json.forEach((virus, i) => { // Render the JSON data to the HTML
            let row = '<tr>' +
                '<th scope="row" >' + virus.id + '</th>' +
                '<td>' + virus.name + '</td>' +
                '<td>' + virus.magnitude + '</td>' +
                '<td>' + virus.releasedOn + '</td>';

            if (virus.editable) {
                row = row +
                    '<td><a href="/virus/edit/' + virus.id.toString() +
                    '" class="btn btn btn-outline-secondary font-weight-normal mb-3">Edit</a></td>' +
                    '<td><a href="/virus/delete/' + virus.id.toString() +
                    '" class="btn btn btn-outline-secondary font-weight-normal mb-3">Delete</a></td>';
            }

            row = row + '</tr>';
            $('#list-table-body').append(row);
        }));
}

function fetchCapitalData() {
    fetch('/capital/fetch') // Fetch the data (GET request)
        .then((response) => response.json()) // Extract the JSON from the Response
        .then((json) => json.forEach((capital, i) => { // Render the JSON data to the HTML
            let row = '<tr>' +
                '<th scope="row" >' + capital.id + '</th>' +
                '<td>' + capital.name + '</td>' +
                '<td>' + capital.latitude + '</td>' +
                '<td>' + capital.longitude + '</td>' +
                '</tr>';
            $('#list-table-body').append(row);
        }));
}

function controlListData() {
    // jQuery Event handlers
    let radioVirus = $('#radioViruses');
    radioVirus.unbind("click");
    radioVirus.click(() => {
        listVirusData();
    });

    let radionCapitals = $('#radioCapitals')
    radionCapitals.unbind("click");
    radionCapitals.click(() => {
        listCapitalData();
    });

    $('#clear-button').click(() => $('.data-container').empty()); // Clear the data
}