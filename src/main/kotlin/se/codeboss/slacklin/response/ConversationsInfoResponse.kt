package se.codeboss.slacklin.response

import se.codeboss.slacklin.model.Channel


/*
{
    "ok": true,
    "channel": {
        "id": "C012AB3CD",
        "created": 1507235627,
        "is_im": true,
        "is_org_shared": false,
        "user": "U27FFLNF4",
        "last_read": "1513718191.000038",
        "latest": {
            "type": "message",
            "user": "U5R3PALPN",
            "text": "Psssst!",
            "ts": "1513718191.000038"
        },
        "unread_count": 0,
        "unread_count_display": 0,
        "is_open": true,
        "locale": "en-US",
        "priority": 0.043016851216706
    }
}
*/
data class ConversationsInfoResponse(
    val ok: Boolean,
    val channel: Channel
)