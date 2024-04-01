function edit() {
    const form = document.querySelector("#frm");


    const product = {};
    ['id', 'name', 'sellingPrice', 'supplyingPrice', 'description', 'img']
        .forEach(column => product[column] = form.querySelector('.${column}').value);

    const xhr = new XMLHttpRequest();

    xhr.open("PUT","/admin/products/"+product.id);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(JSON.stringify(product));

    xhr.onload = function () {
        if (xhr.responseText === "success") {
            window.location.href = "/admin/products";
        }
    };
}
