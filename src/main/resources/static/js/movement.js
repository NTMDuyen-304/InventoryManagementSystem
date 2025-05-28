document.addEventListener("DOMContentLoaded", function () {
  console.log("movement.js: DOM loaded");

  // === Sidebar toggle ===
  // Selects the sidebar toggle button, close button, sidebar itself, and content area.
  const sidebarToggle = document.getElementById("sidebarToggle");
  const sidebarClose = document.getElementById("sidebarClose");
  const sidebar = document.getElementById("sidebar");
  const content = document.getElementById("content");

  // Adds event listener to the sidebar toggle button to hide/show the sidebar
  // and expand/contract the main content area.
  if (sidebarToggle) {
    sidebarToggle.addEventListener("click", function () {
      sidebar.classList.toggle("hidden");
      content.classList.toggle("expanded");
    });
  }

  // Adds event listener to the sidebar close button for the same toggle functionality.
  if (sidebarClose) {
    sidebarClose.addEventListener("click", function () {
      sidebar.classList.toggle("hidden");
      content.classList.toggle("expanded");
    });
  }

  // === Active sidebar link ===
  // Adds click listeners to all elements with the 'sidebar-link' class.
  // When a link is clicked, it removes 'active' from all other links and adds 'active' to itself.
  document.querySelectorAll(".sidebar-link").forEach((link) => {
    link.addEventListener("click", function () {
      document
        .querySelectorAll(".sidebar-link")
        .forEach((l) => l.classList.remove("active"));
      this.classList.add("active");
    });
  });

  // Sets the 'active' class on the sidebar link that matches the current URL path.
  const path = window.location.pathname;
  document.querySelectorAll(".sidebar-link").forEach((link) => {
    if (link.getAttribute("href") === path) {
      link.classList.add("active");
    } else {
      link.classList.remove("active");
    }
  });

  // === Movement-specific features ===

  // Call the function to highlight rows based on action type (IMPORT/EXPORT).
  // This function is defined outside DOMContentLoaded to be globally accessible if needed,
  // but called here on page load.
  highlightMovementRows();

  // Confirm before navigating to the create movement page.
  // Selects the "Record Movement" button (assuming it's an anchor tag with btn-success class).
  const recordBtn = document.querySelector("a.btn-success");
  if (recordBtn) {
    recordBtn.addEventListener("click", function (e) {
      // Displays a confirmation dialog to the user.
      const confirmed = confirm("Do you want to record a new stock movement?");
      if (!confirmed) {
        // If the user cancels, prevent the default link navigation.
        e.preventDefault();
      }
    });
  }

  // Optional: Auto-focus on a search input if it exists on the page.
  // This assumes there might be a search input for movements.
  const searchInput = document.querySelector('input[name="keyword"]');
  if (searchInput) {
    searchInput.focus();
  }

  // Optional: Add a fade-in effect to table rows when loaded.
  // This can provide a smoother visual experience, especially after filtering or initial load.
  const tableRows = document.querySelectorAll("tbody tr");
  // Only apply if there's more than just a "no data" row.
  if (tableRows.length > 1) {
    tableRows.forEach((row, index) => {
      // Adds a 'fade-in' class which can be styled with CSS for animation.
      row.classList.add("fade-in");
      // Optional: Add a slight delay for a staggered effect.
      // row.style.animationDelay = `${index * 50}ms`;
    });
  }
});

// Function to add visual indicators (colors and bold text) to table rows
// based on whether the movement action is "IMPORT" or "EXPORT".
function highlightMovementRows() {
  // Selects all table rows within the tbody.
  const rows = document.querySelectorAll("tbody tr");

  rows.forEach((row) => {
    // Assumes the action column is the third child (index 2) of the row.
    // Adjust this index if your table structure changes.
    const actionCell = row.children[2];
    if (!actionCell) return; // Exit if the cell doesn't exist.

    // Get the trimmed text content of the action cell.
    const actionText = actionCell.textContent.trim();

    // Apply Bootstrap classes based on the action.
    if (actionText === "IMPORT") {
      actionCell.classList.add("text-success", "fw-bold"); // Green for incoming stock
    } else if (actionText === "EXPORT") {
      actionCell.classList.add("text-danger", "fw-bold"); // Red for outgoing stock
    }
  });
}
