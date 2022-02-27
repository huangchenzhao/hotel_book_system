module.exports = function (grunt) {
    grunt.initConfig({
        mock: {
            options: {
                port:'3001',
                host:'localhost',
                debug: true,
            },
            api:{
                // 这里配置 route 所在的文件
                cwd: 'mock',
                // 路由配置文件
                src: ['*.json', '*.js']
            }

        }
    });
    grunt.loadNpmTasks('grunt-restful-mock');
    grunt.registerTask('default', ['mock']);
}
