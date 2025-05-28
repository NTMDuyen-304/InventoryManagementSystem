document.addEventListener("DOMContentLoaded", function () {
  const form = document.getElementById("stockMovementForm");
  const movementTypeRadios = document.querySelectorAll('input[name="type"]');
  const productSelect = document.getElementById("productId");
  const quantityInput = document.getElementById("quantity");
  const reasonInput = document.getElementById("reason");
  const summaryBox = document.getElementById("movementSummary");

  const currentStockSpan = document.getElementById("currentStock");
  const newStockSpan = document.getElementById("newStockLevel");
  const movementTypeSpan = document.getElementById("summaryMovementType");
  const productNameSpan = document.getElementById("summaryProductName");

  // Mocked stock values (in real usage, should be updated from server or via data-* attributes)
  const productStockMap = {};
  document.querySelectorAll("#productId option[data-stock]").forEach((opt) => {
    productStockMap[opt.value] = {
      name: opt.dataset.name,
      stock: parseInt(opt.dataset.stock),
    };
  });

  function updateSummary() {
    const productId = productSelect.value;
    const quantity = parseInt(quantityInput.value || "0");
    const type = document.querySelector('input[name="type"]:checked')?.value;

    if (!productId || !type || !productStockMap[productId]) {
      summaryBox.style.display = "none";
      return;
    }

    const stock = productStockMap[productId].stock;
    const productName = productStockMap[productId].name;
    const newStock = type === "incoming" ? stock + quantity : stock - quantity;

    productNameSpan.textContent = productName;
    currentStockSpan.textContent = stock;
    newStockSpan.textContent = newStock;
    movementTypeSpan.textContent =
      type === "incoming" ? "Incoming" : "Outgoing";
    movementTypeSpan.className =
      type === "incoming" ? "text-green-600" : "text-red-600";

    summaryBox.style.display = "block";
  }

  // Update summary on change
  productSelect.addEventListener("change", updateSummary);
  quantityInput.addEventListener("input", updateSummary);
  movementTypeRadios.forEach((radio) => {
    radio.addEventListener("change", updateSummary);
  });

  // Form validation before submit
  form.addEventListener("submit", function (e) {
    let hasError = false;
    const productId = productSelect.value;
    const quantity = parseInt(quantityInput.value || "0");
    const reason = reasonInput.value.trim();
    const type = document.querySelector('input[name="type"]:checked')?.value;
    const errorDiv = document.getElementById("formErrors");
    errorDiv.innerHTML = "";
    errorDiv.style.display = "none";

    if (!productId) {
      errorDiv.innerHTML += '<p class="text-red-600">Product is required.</p>';
      hasError = true;
    }
    if (quantity <= 0) {
      errorDiv.innerHTML +=
        '<p class="text-red-600">Quantity must be greater than 0.</p>';
      hasError = true;
    }
    if (!reason) {
      errorDiv.innerHTML += '<p class="text-red-600">Reason is required.</p>';
      hasError = true;
    }
    if (
      type === "outgoing" &&
      productStockMap[productId] &&
      quantity > productStockMap[productId].stock
    ) {
      errorDiv.innerHTML += `<p class="text-red-600">Not enough stock available. Current stock: ${productStockMap[productId].stock}</p>`;
      hasError = true;
    }

    if (hasError) {
      e.preventDefault();
      errorDiv.style.display = "block";
    }
  });
});
