var app = new Vue({
    el: '#app',
    data: {
        tasks: []
    },
    created() {
        fetch('/tasks/')
            .then(response => response.json())
            .then(tasks => {
                this.tasks = tasks;
            });
    }
});