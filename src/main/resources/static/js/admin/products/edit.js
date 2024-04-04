function edit() {
    const form = document.querySelector("#frm");

    function getValue(form, className) {
        return form.querySelector(`.${className}`).value;
    }

    const product = {
        id: getValue(form, "id"),
        name: getValue(form, "name"),
        sellingPrice: getValue(form, "sellingPrice"),
        supplyingPrice: getValue(form, "supplyingPrice"),
        description: getValue(form, "description"),
        img: getValue(form, "img")
    };

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
