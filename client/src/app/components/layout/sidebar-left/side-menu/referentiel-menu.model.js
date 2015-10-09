export default {
    name: 'Référentiels',
    icon: 'insert_chart',
    type: 'dropdown',
    priority: 2,
    children: [{
        name: 'Centres de service',
        state: 'referentiel.cds',
        type: 'link'
    }, {
        name: 'Clients',
        state: 'referentiel.clients',
        type: 'link'
    }, {
        name: 'Statuts bons de commandes',
        state: 'referentiel.statutbc',
        type: 'link'
    }, {
        name: 'Types de devis',
        state: 'referentiel.typedevis',
        type: 'link'
    }]
};