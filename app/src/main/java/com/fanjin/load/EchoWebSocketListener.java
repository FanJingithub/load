package com.fanjin.load;

import android.util.Log;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public final class EchoWebSocketListener extends WebSocketListener {
    private static final String TAG = "EchoWebSocketListener";

    @Override
    public void onOpen(WebSocket webSocket, Response response) {

        Log.e(TAG, "onOpen: hello！");
        webSocket.send("hello world");
        webSocket.send("welcome");
        webSocket.send(ByteString.decodeHex("adef"));
        //webSocket.close(1000, "bye");
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        Log.e(TAG, "onMessage: " + text);
    }

    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {
        Log.e(TAG, "onMessage: " + bytes);
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        webSocket.close(1000, null);
        Log.e(TAG, "onClosing: " + reason);
    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {
        Log.e(TAG, "onClosed: " + reason);
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        Log.e(TAG, "onFailure: "+ t.getMessage());
    }
}