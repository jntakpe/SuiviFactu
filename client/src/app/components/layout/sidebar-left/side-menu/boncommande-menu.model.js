export default {
    name: 'Bons de commande',
    icon: 'insert_chart',
    type: 'dropdown',
    priority: 1,
    children: [{
        name: 'Suivi',
        state: 'boncommande.suivi',
        type: 'link'
    }, {
        name: 'Nouveau',
        state: 'boncommande.new',
        type: 'link'
    }]
};