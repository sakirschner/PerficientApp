<template>
  <div id="main">
      <br><br>
        <h2>Skill</h2><br>
        <label>Name:</label> 
        <input v-model='field.name' placeholder="Java"><br>
        <label>Type:</label>  
        <input v-model='field.type' placeholder="Software Development">
        <br>
        <button v-on:click='addField'>Next</button>
  </div>
</template>

<script>
export default {
    data() {
        return {
            field: {
                name: '',
                type: '',
            }
        }
    },
    methods: {
        addField() {
            fetch(`http://localhost:8080/Perficient/employees/fields`,
                {
                    method: 'POST',
                    headers: {
                        "Content-Type": "application/json"
                },
                body: JSON.stringify(this.field)
            })
            .catch(
                (err) => {
                    console.error(err)
                }
           )
            this.$router.push({ name: 'add-skill' });
        }
    }
}
</script>

<style>

</style>