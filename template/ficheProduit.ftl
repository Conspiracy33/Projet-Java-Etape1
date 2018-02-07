<#ftl encoding="utf-8">
<html>
<head>
  <title>Fiche produit</title>

</head>
<style>

</style>
<body style="height:100%;font-family: helvetica;font-size:14px;">
  <#list ListeProduit as item>
      <div style="height:100%;">
        <p>Code: ${item.getCode()}</p>
        <p style="text-align:right;">Catégorie: ${item.getCategorie()}</p>
        <h1 style="font-size:30px;font-weight:bold;">${item.getNom()}</h1>
        <p>Description</p>
        <div style="border: 3px double black;height:50%;padding-left:2%;padding-right:2%;">
            <p>${item.getDescription()}</p>
        </div>
        <div style="text-align:right">
            <p>Montant HT: ${item.getPrixHT()}</p>
            <p>TVA: ${item.getMontantTva()}</p>
            <p>Montant TTC: ${item.getMontantTTC()}</p>
        </div>
      </div>
      <hr/>
  </#list>
</body>
</html>