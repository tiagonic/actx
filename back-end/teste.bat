@echo off
setlocal

:: Defina o URL base do seu servidor
set BASE_URL=http://localhost:8080/actx/api/transacoes

:: Defina as credenciais de autenticação (usuário:senha)
set AUTH=Basic YWRtaW46cGFzc3dvcmQ=

echo Testando transacaoRequerida...
echo -X POST %BASE_URL%/requerida -H "Content-Type: application/json; charset=UTF-8" -H "Authorization: %AUTH%" -d "{\"login\":\"Transação Automática Requerida\",\"email\":\"joao1@email.com\",\"senha\":\"123\"}"
curl -X POST %BASE_URL%/requerida -H "Content-Type: application/json; charset=UTF-8" -H "Authorization: %AUTH%" -d "{\"login\":\"Transação Automática Requerida\",\"email\":\"joao1@email.com\",\"senha\":\"123\"}"
echo.
echo.

echo Testando transacaoRequerNova...
echo -X POST %BASE_URL%/requerNova -H "Content-Type: application/json; charset=UTF-8" -H "Authorization: %AUTH%" -d "{\"login\":\"Transação Automática Requerida Nova\",\"email\":\"lucas1@email.com\",\"senha\":\"456\"}"
curl -X POST %BASE_URL%/requerNova -H "Content-Type: application/json; charset=UTF-8" -H "Authorization: %AUTH%" -d "{\"login\":\"Transação Automática Requerida Nova\",\"email\":\"lucas1@email.com\",\"senha\":\"456\"}"
echo.
echo.

echo.
echo Testando transacaoObrigatoria...
echo -X POST %BASE_URL%/obrigatoria -H "Content-Type: application/json; charset=UTF-8" -H "Authorization: %AUTH%" -d "{\"login\":\"Transação Automática Obrigatória\",\"email\":\"mateus@email.com\",\"senha\":\"789\"}"
curl -X POST %BASE_URL%/obrigatoria -H "Content-Type: application/json; charset=UTF-8" -H "Authorization: %AUTH%" -d "{\"login\":\"Transação Automática Obrigatória\",\"email\":\"mateus@email.com\",\"senha\":\"789\"}"
echo.
echo.

echo Testando transacaoSuporta...
echo -X GET %BASE_URL%/suporta/1 -H "Authorization: %AUTH%"
curl -X GET %BASE_URL%/suporta/1 -H "Authorization: %AUTH%"
echo.
echo.

echo Testando transacaoNaoSuporta...
echo -X GET %BASE_URL%/naoSuporta -H "Authorization: %AUTH%"
curl -X GET %BASE_URL%/naoSuporta -H "Authorization: %AUTH%"
echo.
echo.

echo Testando transacaoNunca...
echo -X POST %BASE_URL%/nunca -H "Content-Type: application/json; charset=UTF-8" -H "Authorization: %AUTH%" -d "{\"login\":\"Transação Automática Nunca\",\"email\":\"pedro1@email.com\",\"senha\":\"senha do Pedro\"}"
curl -X POST %BASE_URL%/nunca -H "Content-Type: application/json; charset=UTF-8" -H "Authorization: %AUTH%" -d "{\"login\":\"Transação Automática Nunca\",\"email\":\"pedro1@email.com\",\"senha\":\"senha do Pedro\"}"
echo.
echo.

echo Testando transacaoProgramaticaUserTransaction...
echo -X POST %BASE_URL%/programaticaUserTransaction -H "Content-Type: application/json; charset=UTF-8" -H "Authorization: %AUTH%" -d "{\"login\":\"Transação Programática UserTransaction\",\"email\":\"ravi@email.com\",\"senha\":\"senha do Ravi\"}"
curl -X POST %BASE_URL%/programaticaUserTransaction -H "Content-Type: application/json; charset=UTF-8" -H "Authorization: %AUTH%" -d "{\"login\":\"Transação Programática UserTransaction\",\"email\":\"ravi@email.com\",\"senha\":\"senha do Ravi\"}"
echo.
echo.

echo Testando transacaoProgramaticaTransactionManager...
echo -X POST %BASE_URL%/programaticaTransactionManager -H "Content-Type: application/json; charset=UTF-8" -H "Authorization: %AUTH%" -d "{\"login\":\"Transação Programática TransactionManager\",\"email\":\"nina@email.com\",\"senha\":\"senha da Nina\"}"
curl -X POST %BASE_URL%/programaticaTransactionManager -H "Content-Type: application/json; charset=UTF-8" -H "Authorization: %AUTH%" -d "{\"login\":\"Transação Programática TransactionManager\",\"email\":\"nina@email.com\",\"senha\":\"senha da Nina\"}"
echo.
echo.

echo Testes concluídos.

endlocal
pause
