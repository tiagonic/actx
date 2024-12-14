#/bin/bash
BASE_URL=http://localhost:8080/actx/api/transacoes

echo "Testando transacaoRequerida..."
curl -X POST $BASE_URL/requerida -H "Content-Type: application/json; charset=UTF-8" -d "{\"nome\":\"João 1\",\"email\":\"joao1@email.com\",\"senha\":\"123\"}"
echo ""

echo "Testando transacaoRequerNova..."
curl -X POST $BASE_URL/requerNova -H "Content-Type: application/json; charset=UTF-8" -d "{\"nome\":\"Lucas 1\",\"email\":\"lucas1@email.com\",\"senha\":\"456\"}"
echo ""

echo "Testando transacaoObrigatoria..."
curl -X POST $BASE_URL/obrigatoria -H "Content-Type: application/json; charset=UTF-8" -d "{\"nome\":\"Mateus\",\"email\":\"mateus@email.com\",\"senha\":\"789\"}"
echo ""

echo "Testando transacaoSuporta..."
curl -X GET $BASE_URL/suporta/1
echo ""

echo "Testando transacaoNaoSuporta..."
curl -X GET $BASE_URL/naoSuporta
echo ""

echo "Testando transacaoNunca..."
curl -X POST $BASE_URL/nunca -H "Content-Type: application/json; charset=UTF-8" -d "{\"nome\":\"Pedro 1\",\"email\":\"pedro1@email.com\",\"senha\":\"senha do Pedro\"}"
echo ""

echo "Testando transacaoProgramaticaUserTransaction..."
curl -X POST $BASE_URL/programaticaUserTransaction -H "Content-Type: application/json; charset=UTF-8" -d "{\"nome\":\"Ravi\",\"email\":\"ravi@email.com\",\"senha\":\"senha do Ravi\"}"
echo ""

echo "Testando transacaoProgramaticaTransactionManager..."
curl -X POST $BASE_URL/programaticaTransactionManager -H "Content-Type: application/json; charset=UTF-8" -d "{\"nome\":\"Nina\",\"email\":\"nina@email.com\",\"senha\":\"senha da Nina\"}"
echo ""

echo "Testes concluídos!"
