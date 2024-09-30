//Masks
$("#inPreco").mask("000.000.000.000.000,00", { reverse: true });

// Array de Produtos Já Listados diretamente no Código
var products = [
  {
    id: 1,
    name: "Computador M1-TX",
    description: "Intel I7, 16GB, SSD 256, HD 1T",
    price: 4900,
    category: 1,
    promotion: true,
    new: true,
  },
  {
    id: 2,
    name: "Computador M2-TX",
    description: "Intel I7, 32GB, SSD 512, HD 1T",
    price: 5900,
    category: 2,
    promotion: false,
    new: true,
  },
  {
    id: 3,
    name: "Computador M1-T",
    description: "Intel I5, 16GB, HD 1T",
    price: 2900,
    category: 3,
    promotion: false,
    new: false,
  },
];

var categories = [
  { id: 1, name: "Nacional" },
  { id: 2, name: "Importado" },
  { id: 3, name: "Produção Própria" },
];

// OnLoad
loadProducts();

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
    category: categoriaValue,
    promotion: document.querySelector("#inPromo").checked,
    new: true,
  };
  addNewRow(prod);
  products.push(prod);

  document.querySelector("#formProduct").reset();
}

// Load all products
function loadProducts() {
  for (let prod of products) {
    addNewRow(prod);
  }
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
  var category = categories.find((cat) => cat.id === prod.category);
  newRow
    .insertCell()
    .appendChild(document.createTextNode(category ? category.name : ""));

  //Inserindo as Opções
  var options = "";
  if (prod.promotion) {
    options = '<span class="badge text-bg-success me-1">P</span>';
  }
  if (prod.new) {
    options += '<span class="badge text-bg-primary">L</span>';
  }

  cell = newRow.insertCell();
  cell.className = "d-none d-md-table-cell";
  cell.innerHTML = options;
}
