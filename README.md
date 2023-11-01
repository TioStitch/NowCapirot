# NowCapirot
> Um plugin de maçã do capiroto editável que faz com que suas maçãs sejam consumidas imediatamente,
> além de que é possível editar os efeitos e usar uma maçã especial do seu agrado!

**Imagens:**
![image](https://i.imgur.com/UsDnf0p.png)

> Mensagem opcional para o consumo da maçã.
> A mensagem é totalmente editável e possível de desativar.

![image](https://i.imgur.com/NdZjj4g.png)

**Configuração**
```
apple-settings:
#displayName - O nome que a maçã deve conter (Conter não exato).
#no-permission-message - A mensagem do comando de obter maçã (Opcional).
#consume-notification - Notificação ao consumir a maçã.
#consume-message - A mensagem da notificação.
#effect-list - Efeitos da Maçã
#EFEITO : DURAÇÃO : POTÊNCIA
#20 = 1 segundo.
apple-settings:
  displayName: "Maçã do Capiroto"
  auto-cap: true
  cap-seconds: 1

  #Notificações de intervalo.
  cooldown-notification: true
  cooldown-message: "§cPor favor, aguarde %time% para comer a maçã novamente"
  cap-cooldown: 2

  no-permission-message: "§cComando inválido! Tente /ajuda."
  consume-notification: false
  consume-message: "§6§lBOOM §fVocê consumiu a §eMaçã do Capiroto§f!"
  effect-list:
    - NIGHT_VISION:80:2
    - INCREASE_DAMAGE:80:4

#O item dado ao utilizar /cap give <nick>
#enchantments você pode deixar 'enchantments: []'
#caso não busque ter encantamentos na maçã.
#Lore é a descrição da maçã do capiroto.
apple-itemStack:
  displayName: "§dMaçã do Capiroto"
  enchantments:
    - DURABILITY:1
  lore:
    - "§7Ao ingerir esta maçã"
    - "§7você poderá obter efeitos"
    - "§7sub-humanos incríveis."
    - ""
    - "§eClique direito para"
    - "§eingerir a maçã!"
    - ""
    - "§d§lMAÇã MíSTICA"
```
