const deleteRequest = document.getElementById("deleteButton");
const deleteInput = document.getElementById("deleteInput");
const editRequest = document.getElementById("editButton");
const createRequest = document.getElementById("createButton")

function setupListener() {
    deleteRequest.addEventListener('click', function () {
        deletePlace();
    });
    editRequest.addEventListener('click', function () {
        updatePlace();
    });
    createRequest.addEventListener('click', function () {
        createNewPlace();
    })
}

axios.get('control-panel/')
    .then(function () {
        console.info('Get request');
        setupListener();
    });

function deletePlace() {
    if (confirm('Are you sure you want to delete place with id ' + deleteInput.value + '?')) {
        axios.delete('places/' + deleteInput.value);
    } else {
        console.info('place with id ' + deleteInput.value + ' not deleted');
    }
}

function updatePlace() {
    let id = document.getElementById("editId").value;
    let name = document.getElementById("editName").value;
    let cityName = document.getElementById("editCity").value;
    let categoryName = document.getElementById("editCategory").value;
    let ratingName = document.getElementById("editRating").value;
    let isCrossroads = document.getElementById("editCrossroads");
    var boolean;
    boolean = !!isCrossroads.checked;
    if (cityName === null || cityName === '' || categoryName === null || categoryName === ''
        || ratingName === null || ratingName === '') {
        alert("Please, fill all fields on edit!");
        return;
    }
    if (confirm('Are you sure you want to update place with id ' + id + '?')) {
        if (id !== null && name !== null && cityName !== null && categoryName !== null && ratingName !== null) {
            var updatedPlace = {
                name: name,
                categoryName: categoryName,
                ratingName: ratingName,
                cityName: cityName,
                crossroads: boolean
            };
            axios.put('places/' + id, updatedPlace)
                .catch(function (reason) {
                    console.error(reason);
                });
        }
    }
}

function createNewPlace() {
    let newName = document.getElementById('newName').value;
    let cityName = document.getElementById('cityName').value;
    let categoryName = document.getElementById('categoryName').value;
    let ratingName = document.getElementById('ratingName').value;
    let crossroads = document.getElementById('crossroads');
    var boolean;
    boolean = !!crossroads.checked;
    if (cityName === null || cityName === '' || categoryName === null || categoryName === ''
        || ratingName === null || ratingName === '' || newName === null || newName === '') {
        alert("Please, fill all fields to create new place!");
        return;
    }
    if (newName !== null && cityName !== null && categoryName !== null && ratingName !== null) {
        var newPlace = {
            name: newName,
            categoryName: categoryName,
            ratingName: ratingName,
            cityName: cityName,
            crossroads: boolean
        };
        axios.post('places/', newPlace)
            .then(function () {
                console.info("Created!");
                location.reload();
            })
            .catch(function (reason) {
                console.error(reason);
            });
    }
}