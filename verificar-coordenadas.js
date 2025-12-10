/**
 * Script para verificar se as coordenadas do DataLoader.java
 * correspondem ao HTML original do mapa da UESC
 * 
 * Executar: node verificar-coordenadas.js
 */

// Dados extraídos do HTML original (site da UESC)
const htmlOriginal = [
  { id: 1, nome: "Hospital Veterinário", top: 6, left: 25 },
  { id: 2, nome: "Campo de Futebol", top: 22, left: 21 },
  { id: 3, nome: "Piscina Semi-Olímpica", top: 31, left: 22.5 },
  { id: 4, nome: "Pavilhão de Educação Física", top: 31, left: 12 },
  { id: 5, nome: "Quadra Poliesportiva", top: 37, left: 18 },
  { id: 6, nome: "Restaurante Universitário", top: 26, left: 30 },
  { id: 7, nome: "Centro de Microscopia Eletrônica", top: 28, left: 36 },
  { id: 8, nome: "Pavilhão Waldir Pires", top: 24.5, left: 40.5 },
  { id: 9, nome: "Pavilhão Pedro Calmon", top: 38, left: 26 },
  { id: 10, nome: "Pavilhão Adonias Filho", top: 28, left: 51 },
  { id: 11, nome: "Pavilhão Professor Max de Menezes", top: 49, left: 43 },
  { id: 12, nome: "Centro de Computação Avançada e Multidisciplinar", top: 58, left: 45.5 },
  { id: 13, nome: "Guarita", top: 64, left: 52 },
  { id: 14, nome: "Brasão UESC e Praça das Bandeiras", top: 55, left: 54 },
  { id: 15, nome: "Torre Administrativa", top: 22, left: 54.5 },
  { id: 16, nome: "Pavilhão Jorge Amado", top: 33, left: 63 },
  { id: 17, nome: "Quadra Poliesportiva 2", top: 60, left: 62 },
  { id: 18, nome: "Centro de Biotecnologia e Genética", top: 58, left: 72 },
  { id: 19, nome: "Pavilhão Agroindústria", top: 29, left: 75.5 },
  { id: 20, nome: "Instituto de Pesquisas e Análise Físico-Químicas", top: 32, left: 82 },
  { id: 21, nome: "Centro de Pesquisas em Ciências e Tecnologias das Radiações", top: 37, left: 86 },
  { id: 22, nome: "Pavilhão Manoel Fontes Nabuco", top: 47, left: 79 },
  { id: 23, nome: "Biblioteca Central", top: 63, left: 82 },
  { id: 24, nome: "Auditório Paulo Souto", top: 74, left: 86 },
  { id: 25, nome: "Pavilhão Júlio Cezar de Mattos Cascardo", top: 55, left: 89 },
  { id: 26, nome: "Pavilhão Prof. Evandro Sena Freire", top: 19, left: 70 },
  { id: 27, nome: "Oficina de Manutenção e Estacionamento", top: 27, left: 70 },
  { id: 28, nome: "Núcleo de Educação a Distância", top: 26, left: 73.5 },
  { id: 29, nome: "Estação de Manutenção e Recria de Animais", top: 40.5, left: 84 },
  { id: 30, nome: "Placa comemorativa pelos Direitos Humanos", top: 49, left: 57 },
  { id: 31, nome: "Placa em homenagem a José Haroldo Castro Vieira", top: 34, left: 56 },
  { id: 32, nome: "Base Ambiental", top: 46, left: 33 },
  { id: 33, nome: "Bosque", top: 41, left: 63 },
  { id: 34, nome: "Horto de Plantas Medicinais", top: 23, left: 47 },
  { id: 35, nome: "Casa de Vegetação 1", top: 20, left: 35 },
  { id: 36, nome: "Casa de Vegetação 2", top: 26, left: 45 },
  { id: 37, nome: "Casa de Vegetação 3", top: 53, left: 68 },
  { id: 38, nome: "Horto Florestal da UESC", top: 12, left: 53 },
  { id: 39, nome: "Laboratório Biorrefinarias Integradas (LAPROBIO)", top: 30, left: 80 },
  { id: 40, nome: "Chocosol", top: 30.8, left: 77.6 },
  { id: 41, nome: "Cervejaria/Cachaçaria", top: 30, left: 72 },
  { id: 42, nome: "Arena Ramon Vane", top: 38, left: 60.5 },
  { id: 43, nome: "Complexo de laboratórios para as Ciências Exatas (CLCE)", top: 43, left: 40 },
  { id: 44, nome: "Núcleo de Estudos e Pesquisas Arqueológicas da Bahia (NEPAB)", top: 41, left: 36 },
  { id: 45, nome: "Ciências Sociais", top: 27, left: 15 },
  { id: 46, nome: "Germoplasma", top: 21, left: 31 },
  { id: 47, nome: "Laboratório de Pesquisa em Nutrição e Alimentação de Ruminantes (LAPNAR)", top: 17, left: 33.2 },
  { id: 48, nome: "Crematório", top: 2.7, left: 27.5 },
];

// Dados do DataLoader.java (coordenadaX = left, coordenadaY = top)
const dataLoader = [
  { id: 1, nome: "Hospital Veterinário", coordenadaX: 25.0, coordenadaY: 6.0 },
  { id: 2, nome: "Campo de Futebol", coordenadaX: 21.0, coordenadaY: 22.0 },
  { id: 3, nome: "Piscina Semi-Olímpica", coordenadaX: 22.5, coordenadaY: 31.0 },
  { id: 4, nome: "Pavilhão de Educação Física", coordenadaX: 12.0, coordenadaY: 31.0 },
  { id: 5, nome: "Quadra Poliesportiva", coordenadaX: 18.0, coordenadaY: 37.0 },
  { id: 6, nome: "Restaurante Universitário", coordenadaX: 30.0, coordenadaY: 26.0 },
  { id: 7, nome: "Centro de Microscopia Eletrônica", coordenadaX: 36.0, coordenadaY: 28.0 },
  { id: 8, nome: "Pavilhão Waldir Pires", coordenadaX: 40.5, coordenadaY: 24.5 },
  { id: 9, nome: "Pavilhão Pedro Calmon", coordenadaX: 26.0, coordenadaY: 38.0 },
  { id: 10, nome: "Pavilhão Adonias Filho", coordenadaX: 51.0, coordenadaY: 28.0 },
  { id: 11, nome: "Pavilhão Professor Max de Menezes", coordenadaX: 43.0, coordenadaY: 49.0 },
  { id: 12, nome: "Centro de Computação Avançada e Multidisciplinar", coordenadaX: 45.5, coordenadaY: 58.0 },
  { id: 13, nome: "Guarita", coordenadaX: 52.0, coordenadaY: 64.0 },
  { id: 14, nome: "Brasão UESC e Praça das Bandeiras", coordenadaX: 54.0, coordenadaY: 55.0 },
  { id: 15, nome: "Torre Administrativa", coordenadaX: 54.5, coordenadaY: 22.0 },
  { id: 16, nome: "Pavilhão Jorge Amado", coordenadaX: 63.0, coordenadaY: 33.0 },
  { id: 17, nome: "Quadra Poliesportiva 2", coordenadaX: 62.0, coordenadaY: 60.0 },
  { id: 18, nome: "Centro de Biotecnologia e Genética", coordenadaX: 72.0, coordenadaY: 58.0 },
  { id: 19, nome: "Pavilhão Agroindústria", coordenadaX: 75.5, coordenadaY: 29.0 },
  { id: 20, nome: "Instituto de Pesquisas e Análise Físico-Químicas", coordenadaX: 82.0, coordenadaY: 32.0 },
  { id: 21, nome: "Centro de Pesquisas em Ciências e Tecnologias das Radiações", coordenadaX: 86.0, coordenadaY: 37.0 },
  { id: 22, nome: "Pavilhão Manoel Fontes Nabuco", coordenadaX: 79.0, coordenadaY: 47.0 },
  { id: 23, nome: "Biblioteca Central", coordenadaX: 82.0, coordenadaY: 63.0 },
  { id: 24, nome: "Auditório Paulo Souto", coordenadaX: 86.0, coordenadaY: 74.0 },
  { id: 25, nome: "Pavilhão Júlio Cezar de Mattos Cascardo", coordenadaX: 89.0, coordenadaY: 55.0 },
  { id: 26, nome: "Pavilhão Prof. Evandro Sena Freire", coordenadaX: 70.0, coordenadaY: 19.0 },
  { id: 27, nome: "Oficina de Manutenção e Estacionamento", coordenadaX: 70.0, coordenadaY: 27.0 },
  { id: 28, nome: "Núcleo de Educação a Distância", coordenadaX: 73.5, coordenadaY: 26.0 },
  { id: 29, nome: "Estação de Manutenção e Recria de Animais", coordenadaX: 84.0, coordenadaY: 40.5 },
  { id: 30, nome: "Placa comemorativa pelos Direitos Humanos", coordenadaX: 57.0, coordenadaY: 49.0 },
  { id: 31, nome: "Placa em homenagem a José Haroldo Castro Vieira", coordenadaX: 56.0, coordenadaY: 34.0 },
  { id: 32, nome: "Base Ambiental", coordenadaX: 33.0, coordenadaY: 46.0 },
  { id: 33, nome: "Bosque", coordenadaX: 63.0, coordenadaY: 41.0 },
  { id: 34, nome: "Horto de Plantas Medicinais", coordenadaX: 47.0, coordenadaY: 23.0 },
  { id: 35, nome: "Casa de Vegetação 1", coordenadaX: 35.0, coordenadaY: 20.0 },
  { id: 36, nome: "Casa de Vegetação 2", coordenadaX: 45.0, coordenadaY: 26.0 },
  { id: 37, nome: "Casa de Vegetação 3", coordenadaX: 68.0, coordenadaY: 53.0 },
  { id: 38, nome: "Horto Florestal da UESC", coordenadaX: 53.0, coordenadaY: 12.0 },
  { id: 39, nome: "Laboratório Biorrefinarias Integradas (LAPROBIO)", coordenadaX: 80.0, coordenadaY: 30.0 },
  { id: 40, nome: "Chocosol", coordenadaX: 77.6, coordenadaY: 30.8 },
  { id: 41, nome: "Cervejaria/Cachaçaria", coordenadaX: 72.0, coordenadaY: 30.0 },
  { id: 42, nome: "Arena Ramon Vane", coordenadaX: 60.5, coordenadaY: 38.0 },
  { id: 43, nome: "Complexo de laboratórios para as Ciências Exatas (CLCE)", coordenadaX: 40.0, coordenadaY: 43.0 },
  { id: 44, nome: "Núcleo de Estudos e Pesquisas Arqueológicas da Bahia (NEPAB)", coordenadaX: 36.0, coordenadaY: 41.0 },
  { id: 45, nome: "Ciências Sociais", coordenadaX: 15.0, coordenadaY: 27.0 },
  { id: 46, nome: "Germoplasma", coordenadaX: 31.0, coordenadaY: 21.0 },
  { id: 47, nome: "Laboratório de Pesquisa em Nutrição e Alimentação de Ruminantes (LAPNAR)", coordenadaX: 33.2, coordenadaY: 17.0 },
  { id: 48, nome: "Crematório", coordenadaX: 27.5, coordenadaY: 2.7 },
];

console.log("=".repeat(80));
console.log("VERIFICAÇÃO DE COORDENADAS - DataLoader vs HTML Original");
console.log("=".repeat(80));
console.log("");
console.log("Legenda: HTML (top, left) vs DataLoader (coordenadaY, coordenadaX)");
console.log("");

let erros = 0;
let corretos = 0;

for (let i = 0; i < htmlOriginal.length; i++) {
  const html = htmlOriginal[i];
  const data = dataLoader[i];
  
  // No HTML: top = Y, left = X
  // No DataLoader: coordenadaX = X (left), coordenadaY = Y (top)
  const xCorreto = html.left === data.coordenadaX;
  const yCorreto = html.top === data.coordenadaY;
  
  if (xCorreto && yCorreto) {
    corretos++;
    console.log(`✅ #${String(html.id).padStart(2, '0')} ${html.nome.substring(0, 40).padEnd(40)} - OK`);
  } else {
    erros++;
    console.log(`❌ #${String(html.id).padStart(2, '0')} ${html.nome.substring(0, 40).padEnd(40)}`);
    if (!xCorreto) {
      console.log(`      X (left): HTML=${html.left}% | DataLoader=${data.coordenadaX}%`);
    }
    if (!yCorreto) {
      console.log(`      Y (top):  HTML=${html.top}% | DataLoader=${data.coordenadaY}%`);
    }
  }
}

console.log("");
console.log("=".repeat(80));
console.log(`RESULTADO: ${corretos} corretos, ${erros} com diferença`);
console.log("=".repeat(80));

if (erros > 0) {
  console.log("");
  console.log("CÓDIGO JAVA PARA CORRIGIR:");
  console.log("-".repeat(80));
  
  for (let i = 0; i < htmlOriginal.length; i++) {
    const html = htmlOriginal[i];
    const data = dataLoader[i];
    
    const xCorreto = html.left === data.coordenadaX;
    const yCorreto = html.top === data.coordenadaY;
    
    if (!xCorreto || !yCorreto) {
      console.log(`// #${String(html.id).padStart(2, '0')} - ${html.nome}`);
      console.log(`local${String(html.id).padStart(2, '0')}.setCoordenadaX(${html.left});`);
      console.log(`local${String(html.id).padStart(2, '0')}.setCoordenadaY(${html.top});`);
      console.log("");
    }
  }
}
