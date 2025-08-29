<template>
  <q-page class="flex flex-center">
    <div style="width: 600px; max-width: 95vw;">
      <h4 class="q-mb-md">Produtos</h4>
      <q-table
        :rows="produtos"
        :columns="columns"
        row-key="id"
        flat
        bordered
        color="primary"
        class="q-mb-lg shadow-2 rounded-borders"
        :pagination="{ rowsPerPage: 10 }"
        no-data-label="Nenhum produto cadastrado"
      >
        <template v-slot:body-cell-produto="props">
          <q-td :props="props">
            <span class="text-bold">{{ props.value }}</span>
          </q-td>
        </template>
        <template v-slot:body-cell-preso="props">
          <q-td :props="props">
            <q-badge color="secondary" align="top">{{ props.value }}</q-badge>
          </q-td>
        </template>
        <template v-slot:body-cell-actions="props">
          <q-td :props="props">
            <q-btn size="sm" color="primary" flat icon="edit" @click="editarProduto(props.row)" class="q-mr-xs" />
            <q-btn size="sm" color="negative" flat icon="delete" @click="removerProduto(props.row.id)" />
          </q-td>
        </template>
      </q-table>
      <div class="q-gutter-md row items-center q-mb-md">
        <q-input v-model="novoProduto" label="Produto" outlined dense class="col" />
        <q-input v-model.number="novoPreso" label="Preso" type="number" outlined dense class="col-3" />
        <q-btn :label="editandoId ? 'Salvar' : 'Adicionar'" @click="editandoId ? salvarEdicao() : adicionarProduto()" color="primary" unelevated />
        <q-btn v-if="editandoId" label="Cancelar" @click="cancelarEdicao" color="grey" flat />
      </div>
      <q-banner v-if="mensagem" class="q-mb-md" :class="mensagemTipo === 'erro' ? 'bg-red-2 text-red-10' : 'bg-green-2 text-green-10'">
        {{ mensagem }}
      </q-banner>
    </div>
  </q-page>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { getProdutos, addProduto, deleteProduto, updateProduto } from '../services/api';

const produtos = ref([]);
const novoProduto = ref('');
const novoPreso = ref(0);
const editandoId = ref(null);
const mensagem = ref('');
const mensagemTipo = ref('');

const columns = [
  { name: 'id', label: 'ID', align: 'left', field: 'id', sortable: true },
  { name: 'produto', label: 'Produto', align: 'left', field: 'produto', sortable: true },
  { name: 'preso', label: 'Preso', align: 'center', field: 'preso', sortable: true },
  { name: 'actions', label: 'Ações', align: 'center', field: 'actions', sortable: false },
];

function mostrarMensagem(msg, tipo = 'sucesso') {
  mensagem.value = msg;
  mensagemTipo.value = tipo;
  setTimeout(() => mensagem.value = '', 2500);
}

async function carregarProdutos() {
  try {
    const res = await getProdutos();
    produtos.value = res.data;
  } catch {
    mostrarMensagem('Erro ao buscar produtos', 'erro');
  }
}

async function adicionarProduto() {
  if (!novoProduto.value) return;
  try {
    await addProduto(novoProduto.value, novoPreso.value);
    mostrarMensagem('Produto adicionado!');
    novoProduto.value = '';
    novoPreso.value = 0;
    await carregarProdutos();
  } catch {
    mostrarMensagem('Erro ao adicionar produto', 'erro');
  }
}

function editarProduto(row) {
  editandoId.value = row.id;
  novoProduto.value = row.produto;
  novoPreso.value = row.preso;
}

async function salvarEdicao() {
  if (!novoProduto.value || editandoId.value === null) return;
  try {
    await updateProduto(editandoId.value, novoProduto.value, novoPreso.value);
    mostrarMensagem('Produto atualizado!');
    editandoId.value = null;
    novoProduto.value = '';
    novoPreso.value = 0;
    await carregarProdutos();
  } catch {
    mostrarMensagem('Erro ao atualizar produto', 'erro');
  }
}

function cancelarEdicao() {
  editandoId.value = null;
  novoProduto.value = '';
  novoPreso.value = 0;
}

async function removerProduto(id) {
  if (!confirm('Tem certeza que deseja remover este produto?')) return;
  try {
    await deleteProduto(id);
    mostrarMensagem('Produto removido!');
    await carregarProdutos();
  } catch {
    mostrarMensagem('Erro ao remover produto', 'erro');
  }
}

onMounted(carregarProdutos);
</script>
