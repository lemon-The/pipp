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

const addCountry = async () => {
  //await fetch('http://localhost:8080/country/save');

  let country = { };
  country.name = document.querySelector('#countryName');
  country.side = document.querySelector('#countrySide');

  let response = await fetch("http://localhost:8080/country/save", {
    method: "POST",
    headers: {
      'Content-Type': 'application/json;charset=utf-8'
    },
    body: JSON.stringify(country)
  });

}

document.getElementById("addClass").onclick = addCountry;


const renderCountries = async () => {
  const countries = await fetch('http://localhost:8080/country/get')
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
