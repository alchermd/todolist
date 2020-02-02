var app = new Vue({
    el: '#app',
    data: {
        tasks: []
    },
    created() {
        fetch('/tasks/')
            .then(response => response.json())
            .then(tasks => {
                this.tasks = tasks.sort(this.compareById);
            });
    },
    methods: {
        updateTask(event, task) {
            fetch(`/tasks/${task.id}/`, {
                method: 'put',
                body: JSON.stringify(task),
                headers: {
                    'Content-Type': 'application/json',
                }
            });
        },
        compareById(a, b) {
            if (a.id < b.id) {
                return -1;
            }

            return 1;
        },
    },
});