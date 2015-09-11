export default function runBlock(toastr, Restangular, baseUrl) {
    'ngInject';
    toastr.options = {
        progressBar: true,
        closeButton: true
    };

    Restangular.setBaseUrl(baseUrl + '/api');

}