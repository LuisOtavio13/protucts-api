import axios from 'axios';

const api = axios.create({
  baseURL:'https://protucts-api-production.up.railway.app/api', // ajuste se necessário
});


export function getProdutos() {
  return api.get('');
}

export function addProduto(produto, preso) {
  // O backend espera um DTO: { produto, preso }
  return api.post('', { produto, preso });
}

export function deleteProduto(id) {
  return api.delete(`/${id}`);
}

export function updateProduto(id, produto, preso) {
  return api.put(`/${id}`, { produto, preso });
}
