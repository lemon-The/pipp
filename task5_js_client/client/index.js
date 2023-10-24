const renderBattles = async () => {
  const battles = await fetch('http://localhost:8080/battles/')
    .then(r => r.json())
  const tbl = document.querySelector('#battles');

  for (let i = tbl.rows.length-1; i > 0; i--) {
    tbl.deleteRow(i);
  }

  models.forEach(m => {
    const row = tbl.insertRow(-1);
    row.insertCell(0).innerHTML = m.name
    row.insertCell(1).innerHTML = m.date
  });


  console.log(models);
}
//renderBattles()
//

//const addCountry = async () => {
//  let country = { };
//  country.name = document.getElementById("countryName").value;
//  country.side = document.getElementById("countrySide").value;
//  console.log(JSON.stringify(country));
//
//  let response = await fetch("http://localhost:8080/country/save", {
//    method: "POST",
//    headers: {
//      'Content-Type': 'application/json;charset=utf-8'
//    },
//    body: JSON.stringify(country)
//  });
//
//  $("#exampleModal").modal('hide');
//}

//document.getElementById("addCountry").onclick = addCountry;

const renderCountries = async () => {
  const countries = await fetch('http://localhost:8080/country/')
    .then(r => r.json())
  const tbl = document.querySelector('#countries');

  for (let i = tbl.rows.length-1; i > 0; i--) {
    tbl.deleteRow(i);
  }

  countries.forEach(c => {
    const row = tbl.insertRow(-1);
    row.insertCell(0).innerHTML = c.name
    row.insertCell(1).innerHTML = c.side
  });


  console.log(countries);
}
renderCountries()

////////////////////////////////////////////////////////////

const addWarship = async () => {
  const warship = { };
  warship.name = document.getElementById("warshipName").value;
  warship.shipClass = document.getElementById("warshipClass").value;
  warship.commissionDate = document.getElementById("warshipCommissionDate").value;
  warship.decommissionDate = document.getElementById("warshipCommissionDate").value;
  warship.country = document.getElementById("countrySelect").value;

  console.log(JSON.stringify(warship));

  let response = await fetch("http://localhost:8080/warships/save", {
    method: "POST",
    headers: {
      'Content-Type': 'application/json;charset=utf-8'
    },
    body: JSON.stringify(warship)
  }).then(() => renderWarships());

  $("#warhsipModal").modal('hide');
}
document.getElementById("addWarship").onclick = addWarship;

const populateSelectWithCountries = async () => {
  const countries = await fetch('http://localhost:8080/country/')
    .then(r => r.json());
  const slct = document.querySelector('#countrySelect');

  console.log("countries");
  console.log(countries);

  countries.forEach(w => {
	let opt = document.createElement("option");
	opt.value = w.name;
	opt.innerHTML = w.name;
	slct.appendChild(opt);
  });

  //console.log(warships);
}
populateSelectWithCountries();

const renderWarships = async () => {
  const warships = await fetch('http://localhost:8080/warships/')
    .then(r => r.json())
  const tbl = document.querySelector('#warships');

  for (let i = tbl.rows.length-1; i > 0; i--) {
    tbl.deleteRow(i);
  }

  warships.forEach(w => {
    const row = tbl.insertRow(-1);
    row.insertCell(0).innerHTML = w.name;
    row.insertCell(1).innerHTML = w.shipClass;
    row.insertCell(2).innerHTML = w.commissionDate;
    row.insertCell(3).innerHTML = w.decommissionDate;
    row.insertCell(4).innerHTML = w.country.name;
	
	const btnRemove = document.createElement('button');
    btnRemove.innerText = 'Delete';
	btnRemove.classList.add('btn', 'btn-secondary');
	btnRemove.addEventListener('click', () => {
      fetch("http://localhost:8080/warships/delete/" + w.id, {method: "DELETE"}).then(() => renderWarships())
	});
	row.appendChild(btnRemove);
    //row.insertCell(5).innerHTML = btnRemove.innerHTML;

	const btnUpdate = document.createElement('button');
    btnUpdate.innerText = 'Update';
	btnUpdate.classList.add('btn', 'btn-primary');
	btnUpdate.addEventListener('click', () => {
	  $("#warhsipModal").modal('show');
      fetch("http://localhost:8080/warships/update" + w.id, {
        method: "PUT",
        headers: {
          'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(warship)
      }).then(() => renderWarships());

	  $("#warhsipModal").modal('hide');
	});
	row.appendChild(btnUpdate);
    //row.insertCell(6).innerHTML = btnUpdate.innerHTML;
  });


  console.log(warships);
}
renderWarships()

////////////////////////////////////////////////////////////


