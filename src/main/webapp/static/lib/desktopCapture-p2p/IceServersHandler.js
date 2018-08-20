// IceServersHandler.js

var IceServersHandler = (function() {
    function getIceServers(connection) {
        // resiprocate: 3344+4433
        var iceServers = [{
                'urls': [
                    'turn:192.168.186.128:3478'
                ],
                'username': 'ling',
                'credential': 'ling1234'
            },
            {
                'urls': [
                    'stun:192.168.186.128:3478'
                ]
            }
        ];

        return iceServers;
    }

    return {
        getIceServers: getIceServers
    };
})();
