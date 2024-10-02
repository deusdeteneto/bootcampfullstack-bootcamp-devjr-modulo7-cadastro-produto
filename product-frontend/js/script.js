//Masks
$("#inPreco").mask("000.000.000.000.000,00", { reverse: true });

// Array de Produtos Já Listados diretamente no Código
var products = [];

var categories = [];

// OnLoad
loadProducts();
loadCategories();

// Load all categories
function loadCategories() {
  $.ajax({
    url: "http://localhost:8080/categories",
    type: "GET",
    async: false,
    success: (response) => {
      categories = response;
      for (let cat of categories) {
        document.getElementById(
          "selectCategory"
        ).innerHTML += `<option value=${cat.id}>${cat.name}</option>`;
      }
    },
  });
}

// Load all products
function loadProducts() {
  $.getJSON("http://localhost:8080/products", (response) => {
    products = response;
    for (let prod of products) {
      addNewRow(prod);
    }
  });
}

//salvar o produto
function save() {
  //Conversão de String para número Preço
  let precoValor = document.querySelector("#inPreco").value;
  precoValor = parseFloat(precoValor.replace(/\./g, "").replace(",", "."));

  //Conversão de String para número Categoria
  let categoriaValue = parseInt(
    document.querySelector("#selectCategory").value
  );

  let prod = {
    id: products.length + 1,
    name: document.querySelector("#inName").value,
    description: document.querySelector("#inDesc").value,
    price: precoValor,
    idCategory: categoriaValue,
    promotion: document.querySelector("#inPromo").checked,
    newProduct: true,
  };

  $.ajax({
    url: "http://localhost:8080/products",
    type: "POST",
    contentType: "application/json",
    data: JSON.stringify(prod),
    success: (product) => {
      addNewRow(product);
      products.push(product);
      document.querySelector("#formProduct").reset();
    },
  });
}

//Add nova linha
function addNewRow(prod) {
  //Inserir na tabela, nova linha para listar os produtos
  var table = document.querySelector("#productsTable");
  var newRow = table.insertRow();

  //Inserindo os produtos
  newRow.insertCell().appendChild(document.createTextNode(prod.id));
  newRow.insertCell().appendChild(document.createTextNode(prod.name));
  let cellCss = newRow.insertCell();
  cellCss.appendChild(document.createTextNode(prod.description));
  cellCss.className = "d-none d-md-table-cell";

  //Formatação do Preço para BRL
  let formato = new Intl.NumberFormat("pt-BR", {
    style: "currency",
    currency: "BRL",
  });
  newRow
    .insertCell()
    .appendChild(document.createTextNode(formato.format(prod.price)));

  // Inserindo o nome da Category
  var category = categories.find((cat) => cat.id === prod.idCategory);
  newRow
    .insertCell()
    .appendChild(document.createTextNode(category ? category.name : ""));

  //Inserindo as Opções
  var options = "";
  if (prod.promotion) {
    options = '<span class="badge text-bg-success me-1">P</span>';
  }
  if (prod.newProduct) {
    options += '<span class="badge text-bg-primary">L</span>';
  }

  cell = newRow.insertCell();
  cell.className = "d-none d-md-table-cell";
  cell.innerHTML = options;
}
