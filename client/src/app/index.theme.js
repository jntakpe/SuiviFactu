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

    $mdThemingProvider.definePalette('black', {
        '50': 'e1e1e1',
        '100': 'b6b6b6',
        '200': '8c8c8c',
        '300': '646464',
        '400': '4d4d4d',
        '500': '3a3a3a',
        '600': '2f2f2f',
        '700': '232323',
        '800': '1a1a1a',
        '900': '121212',
        'A100': 'ffffff',
        'A200': 'ffffff',
        'A400': 'ffffff',
        'A700': 'ffffff',
        'contrastDefaultColor': 'light'
    });

    $mdThemingProvider.theme('cyan')
        .primaryPalette('cyan')
        .accentPalette('amber')
        .warnPalette('deep-orange');

    $mdThemingProvider.theme('white-cyan')
        .primaryPalette('white')
        .accentPalette('cyan', {'default': '500'})
        .warnPalette('deep-orange');

    $mdThemingProvider.setDefaultTheme('cyan');
}