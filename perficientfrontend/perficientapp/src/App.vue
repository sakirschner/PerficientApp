<template>
  <div id="app">
    <div class="header">
      <br>
    <router-link :to="{ name: 'employees' }" tag="span">View Employees</router-link>
    <router-link :to="{ name: 'add-employee' }" tag="span">Add Employees</router-link>
    <router-view/>
    </div>
  </div>
</template>

<script>

export default {
  name: 'App',
}
</script>

<style>
@import url('https://fonts.googleapis.com/css?family=Roboto');
#app {
  text-align: center;
  margin: 0;
  font-family: Roboto;
}

a {
  color: #cc1f20;
  text-decoration: none;
}

a:hover {
  cursor: pointer;
  text-decoration: none;
}

p {
  margin-top: -5px;
}

h1 {
  font-size: 20px;
}

h2 {
  margin-top: 0px;
  font-size: 16px;
}

span {
  color: #ffffff;
  padding: 20px;
  margin-top: 50px;
  font-size: 16px;
}

span:hover {
  cursor: pointer;
  color: #000000;
}

button {
  border: none;
    padding: 8px 15px;
    color: white;
    background: #cc1f20;
    border-radius: 3px;
    display: block;
    margin: 30px auto;
}

button:hover {
  cursor: pointer;
  text-decoration: none;
}

input, select {
    background: none;
    border-bottom: 1px solid #cc1f20;
    border-top: none;
    border-left: none;
    border-right: none;
    width: 200px;
    margin-bottom: 20px;
}

label {
    margin-right: 20px;
    font-weight: 600;
}

.card {
    width: 430px;
    margin: 50px auto;
    background: #ffffff;
    border-radius: 10px;
    padding: 1px 10px 20px 10px;
    box-shadow: 2px 3px 10px #dfdfdf;
}

.delete {
  background: #a9a9a9;
}

.header {
  height: 60px;
  background:#cc1f20;
  margin: -8px;
}

</style>
