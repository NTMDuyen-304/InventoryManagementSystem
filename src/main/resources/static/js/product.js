document.addEventListener("DOMContentLoaded", function () {
  console.log("DOM loaded");

  // === Sidebar toggle (nếu có) ===
  const sidebarToggle = document.getElementById("sidebarToggle");
  const sidebarClose = document.getElementById("sidebarClose");
  const sidebar = document.getElementById("sidebar");
  const content = document.getElementById("content");

  if (sidebarToggle) {
    sidebarToggle.addEventListener("click", function () {
      sidebar.classList.toggle("hidden");
      content.classList.toggle("expanded");
    });
  }

  if (sidebarClose) {
    sidebarClose.addEventListener("click", function () {
      sidebar.classList.toggle("hidden");
      content.classList.toggle("expanded");
    });
  }

  // === Active sidebar link ===
  document.querySelectorAll(".sidebar-link").forEach((link) => {
    link.addEventListener("click", function () {
      document
        .querySelectorAll(".sidebar-link")
        .forEach((l) => l.classList.remove("active"));
      this.classList.add("active");
    });
  });

  // Tự động active link theo URL path
  const path = window.location.pathname;
  document.querySelectorAll(".sidebar-link").forEach((link) => {
    if (link.getAttribute("href") === path) {
      link.classList.add("active");
    } else {
      link.classList.remove("active");
    }
  });

  // === Book management features ===

  // Các phần tử trên trang quản lý sách
  const searchInput = document.getElementById("searchInput");
  const categoryFilter = document.getElementById("categoryFilter"); // nếu có
  const statusFilter = document.getElementById("statusFilter"); // nếu có
  const resetFiltersBtn = document.getElementById("resetFilters");
  const tableBody = document.querySelector("tbody");
  const entriesText = document.querySelector(".entries-count"); // 1 span hoặc div để hiển thị số bản ghi

  // Hàm debounce giúp giảm tần suất xử lý input
  function debounce(func, wait) {
    let timeout;
    return function (...args) {
      clearTimeout(timeout);
      timeout = setTimeout(() => func.apply(this, args), wait);
    };
  }

  // Hàm lọc sách
  function filterBooks() {
    const searchTerm = searchInput?.value.trim().toLowerCase() || "";
    const category = categoryFilter?.value || "all";
    const status = statusFilter?.value || "all";

    if (!tableBody) return;

    const rows = tableBody.querySelectorAll("tr");
    let visibleCount = 0;

    rows.forEach((row) => {
      // Bỏ qua dòng "no data"
      if (row.querySelector("td[colspan]")) return;

      const name =
        row.querySelector("td:nth-child(2)")?.textContent.toLowerCase() || "";
      const categoryText =
        row.querySelector("td:nth-child(3)")?.textContent.toLowerCase() || "";
      const quantity =
        parseInt(row.querySelector("td:nth-child(4)")?.textContent) || 0;

      const matchesSearch = name.includes(searchTerm);
      const matchesCategory =
        category === "all" || categoryText === category.toLowerCase();
      const matchesStatus =
        status === "all" ||
        (status === "instock" && quantity > 0) ||
        (status === "outofstock" && quantity === 0);

      if (matchesSearch && matchesCategory && matchesStatus) {
        row.style.display = "";
        visibleCount++;
      } else {
        row.style.display = "none";
      }
    });

    if (entriesText) {
      entriesText.textContent = visibleCount;
    }
  }

  // Bật sự kiện lọc
  if (searchInput) {
    searchInput.addEventListener("input", debounce(filterBooks, 300));
  }
  if (categoryFilter) {
    categoryFilter.addEventListener("change", filterBooks);
  }
  if (statusFilter) {
    statusFilter.addEventListener("change", filterBooks);
  }
  if (resetFiltersBtn) {
    resetFiltersBtn.addEventListener("click", () => {
      if (searchInput) searchInput.value = "";
      if (categoryFilter) categoryFilter.value = "all";
      if (statusFilter) statusFilter.value = "all";
      filterBooks();
    });
  }

  // Khởi động filter 1 lần khi load trang
  filterBooks();
});
