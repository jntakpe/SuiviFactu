export default function themingConfig($mdThemingProvider) {
    'ngInject';

    $mdThemingProvider.definePalette('white', {
        '50': 'ffffff',
        '100': 'ffffff',
        '200': 'ffffff',
        '300': 'ffffff',
        '400': 'ffffff',
        '500': 'ffffff',
        '600': 'ffffff',
        '700': 'ffffff',
        '800': 'ffffff',
        '900': 'ffffff',
        'A100': 'ffffff',
        'A200': 'ffffff',
        'A400': 'ffffff',
        'A700': 'ffffff',
        'contrastDefaultColor': 'dark'
    });

    $mdThemingProvider.definePalette('light-cyan', $mdThemingProvider.extendPalette('cyan', {
        'contrastDefaultColor': 'light',
        'contrastLightColors': '500 700 800 900',
        'contrastStrongLightColors': '500 700 800 900'
    }));


    $mdThemingProvider.theme('cyan')
        .primaryPalette('light-cyan')
        .accentPalette('amber')
        .warnPalette('deep-orange');

    $mdThemingProvider.theme('white-cyan')
        .primaryPalette('white')
        .accentPalette('cyan', {'default': '500'})
        .warnPalette('deep-orange');

    $mdThemingProvider.setDefaultTheme('cyan');
}