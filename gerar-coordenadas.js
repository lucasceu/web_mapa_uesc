/**
 * Script para ler coordenadas.txt e atualizar o DataLoader.java
 * 
 * Formato do arquivo: Nome, Latitude, Longitude
 * Exemplo: Hospital Veterinário, -14.79468308661536, -39.17497036543215
 * 
 * Executar: node gerar-coordenadas.js
 */

const fs = require('fs');

// Lê o arquivo de coordenadas
const arquivo = fs.readFileSync('coordenadas.txt', 'utf-8');
const linhas = arquivo.split('\n').filter(l => l.trim().length > 0);

// Lê o DataLoader.java
const dataLoaderPath = 'mapa-backend/src/main/java/com/uesc/mapa_backend/DataLoader.java';
let dataLoader = fs.readFileSync(dataLoaderPath, 'utf-8');

// Mapeamento de nomes para variáveis do DataLoader
const mapeamento = {
  'Hospital Veterinário': 'local01',
  'Campo de Futebol': 'local02',
  'Piscina Semi-Olímpica': 'local03',
  'Pavilhão de Educação Física': 'local04',
  'Quadra Poliesportiva': 'local05',
  'Restaurante Universitário': 'local06',
  'Centro de Microscopia Eletrônica': 'local07',
  'Pavilhão Waldir Pires': 'local08',
  'Pavilhão Pedro Calmon': 'local09',
  'Pavilhão Adonias Filho': 'local10',
  'Pavilhão Professor Max de Menezes': 'local11',
  'Centro de Computação Avançada e Multidisciplinar': 'local12',
  'Guarita': 'local13',
  'Brasão UESC e Praça das Bandeiras': 'local14',
  'Torre Administrativa - Edifício José Haroldo Castro Vieira': 'local15',
  'Pavilhão Jorge Amado': 'local16',
  'Centro de Biotecnologia e Genética': 'local17',
  'Pavilhão Agroindústria': 'local18',
  'Instituto de Pesquisas e Análise Físico-Químicas': 'local19',
  'Centro de Pesquisas em Ciências e Tecnologias das Radiações': 'local20',
  'Pavilhão Manoel Fontes Nabuco': 'local21',
  'Biblioteca Central': 'local22',
  'Auditório Paulo Souto': 'local23',
  'Pavilhão Júlio Cezar de Mattos Cascardo': 'local24',
  'Pavilhão Prof. Evandro Sena Freire': 'local25',
  'Oficina de Manutenção e Estacionamento de Veículos Oficiais': 'local26',
  'Núcleo de Educação a Distância e Laboratórios': 'local27',
  'Estação de Manutenção e Recria de Animais de Laboratório': 'local28',
  'Placa comemorativa pelos Direitos Humanos': 'local29',
  'Placa em homenagem a José Haroldo Castro Vieira': 'local30',
  'Base Ambiental': 'local31',
  'Bosque': 'local32',
  'Horto de Plantas Medicinais': 'local33',
  'Casa de Vegetação 1': 'local34',
  'Casa de Vegetação 2': 'local35',
  'Casa de Vegetação 3': 'local36',
  'Horto Florestal da UESC': 'local37',
  'Laboratório Biorrefinarias Integradas (LAPROBIO)': 'local38',
  'Chocosol': 'local39',
  'Cervejaria/Cachaçaria': 'local40',
  'Arena Ramon Vane': 'local41',
  'Complexo de laboratórios para as Ciências Exatas (CLCE)': 'local42',
  'Núcleo de Estudos e Pesquisas Arqueológicas da Bahia (NEPAB)': 'local43',
  'Ciências Sociais': 'local44',
  'Germoplasma': 'local45',
  'Laboratório de Pesquisa em Nutrição e Alimentação de Ruminantes (LAPNAR)': 'local46',
  'Crematório': 'local47',
};

console.log('Processando coordenadas...\n');

let atualizados = 0;
let erros = 0;

for (const linha of linhas) {
  // Divide por vírgula, mas permite apenas 3 partes (nome pode ter vírgula)
  const partes = linha.split(',').map(p => p.trim());
  
  if (partes.length < 2) continue;
  
  const nome = partes[0];
  const latitude = partes[1];
  const longitude = partes[2];
  
  const variavel = mapeamento[nome];
  
  if (!variavel) {
    console.log(`❌ Nome não encontrado: "${nome}"`);
    erros++;
    continue;
  }
  
  if (!latitude || !longitude || latitude === '' || longitude === '') {
    console.log(`⚠️  Sem coordenadas: ${nome}`);
    continue;
  }
  
  // Procura e substitui setLatitude(null) para esse local
  const regexLat = new RegExp(`(${variavel}\\.setLatitude\\()null(\\);)`, 'g');
  const regexLng = new RegExp(`(${variavel}\\.setLongitude\\()null(\\);)`, 'g');
  
  if (dataLoader.match(regexLat)) {
    dataLoader = dataLoader.replace(regexLat, `$1${latitude}$2`);
    dataLoader = dataLoader.replace(regexLng, `$1${longitude}$2`);
    console.log(`✅ ${nome} -> lat: ${latitude}, lng: ${longitude}`);
    atualizados++;
  } else {
    // Se não tem setLatitude, precisa adicionar após setUrlImagem ou setDetalhesSalas
    const regexUrl = new RegExp(`(${variavel}\\.setUrlImagem\\([^)]+\\);)`, 'g');
    const regexDetalhes = new RegExp(`(${variavel}\\.setDetalhesSalas\\([^)]+\\);)`, 'g');
    
    // Verifica se já tem latitude definida (não null)
    const regexLatExistente = new RegExp(`${variavel}\\.setLatitude\\([^n][^)]*\\);`);
    if (dataLoader.match(regexLatExistente)) {
      // Atualiza valor existente
      const regexLatUpdate = new RegExp(`(${variavel}\\.setLatitude\\()[^)]+\\);`, 'g');
      const regexLngUpdate = new RegExp(`(${variavel}\\.setLongitude\\()[^)]+\\);`, 'g');
      dataLoader = dataLoader.replace(regexLatUpdate, `$1${latitude});`);
      dataLoader = dataLoader.replace(regexLngUpdate, `$1${longitude});`);
      console.log(`✅ ${nome} (atualizado) -> lat: ${latitude}, lng: ${longitude}`);
      atualizados++;
    } else if (dataLoader.match(regexDetalhes)) {
      // Adiciona após setDetalhesSalas
      dataLoader = dataLoader.replace(regexDetalhes, `$1\n            ${variavel}.setLatitude(${latitude});\n            ${variavel}.setLongitude(${longitude});`);
      console.log(`✅ ${nome} (adicionado após detalhesSalas) -> lat: ${latitude}, lng: ${longitude}`);
      atualizados++;
    } else if (dataLoader.match(regexUrl)) {
      // Adiciona após setUrlImagem
      dataLoader = dataLoader.replace(regexUrl, `$1\n            ${variavel}.setLatitude(${latitude});\n            ${variavel}.setLongitude(${longitude});`);
      console.log(`✅ ${nome} (adicionado) -> lat: ${latitude}, lng: ${longitude}`);
      atualizados++;
    } else {
      console.log(`❌ Não encontrou onde inserir para: ${nome}`);
      erros++;
    }
  }
}

// Salva o DataLoader atualizado
fs.writeFileSync(dataLoaderPath, dataLoader, 'utf-8');

console.log('\n' + '='.repeat(60));
console.log(`CONCLUÍDO: ${atualizados} locais atualizados, ${erros} erros`);
console.log(`Arquivo salvo: ${dataLoaderPath}`);
console.log('='.repeat(60));
