window.onload = getMenuItems();

function buildOrder() {
  const orderObj = {
            "name": name,
            "quantity": quantity
          }
  return orderObj;
}

async function getOrder(event) { //reload window after finished window.location.reload();
  event.preventDefault();
  const url = "TODO";
  const headers = {
    authorization: {
      'x-api-key': 'TODO'
    }
  }
  console.log("Getting Manu Data...");
  axios.get(url, headers).then((res) => {
    console.log(res.data);
  })
}
async function getMenuItems(event) { //reload window after finished window.location.reload();
  event.preventDefault();
  const url = "TODO";
  const headers = {
    authorization: {
      'x-api-key': 'TODO'
    }
  }
  console.log("Getting Manu Data...");
  axios.get(url, headers).then((res) => {
    console.log(res.data);
  })
}
async function postOrder(event) {
  event.preventDefault();
  const url = "TODO";
  const headers = {
    authorization: {
      'x-api-key': 'TODO'
    }
  }
  const orderObj = buildOrder();

  axios.post(url, orderObj, headers)
  .then((res) => {
    console.log(res);
    window.location.reload();
  })
}
